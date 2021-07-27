package com.yuanbaobao.mini.controller.mini;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuanbaobao.mini.mapper.ProjectDesignMapper;
import com.yuanbaobao.mini.model.Carousel;
import com.yuanbaobao.mini.model.ExchangeDesign;
import com.yuanbaobao.mini.model.ProjectDesign;
import com.yuanbaobao.mini.model.WxUser;
import com.yuanbaobao.mini.result.Result;
import com.yuanbaobao.mini.service.CarouselService;
import com.yuanbaobao.mini.service.ExchangeDesignService;
import com.yuanbaobao.mini.service.ProjectDesignService;
import com.yuanbaobao.mini.service.WxUserService;
import com.yuanbaobao.mini.shiro.model.ShiroUser;
import com.yuanbaobao.mini.shiro.utils.ShiroKit;
import com.yuanbaobao.mini.vo.ProjectDesignRequestVo;
import com.yuanbaobao.mini.vo.ProjectDesignResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/mini/wx/laster")
public class LasterController {

    @Autowired
    CarouselService carouselService;

    @Autowired
    ProjectDesignMapper projectDesignMapper;

    @Autowired
    ProjectDesignService projectDesignService;

    @Autowired
    ExchangeDesignService exchangeDesignService;

    @Autowired
    WxUserService wxUserService;

    /**
     * 查询轮播图
     */
    @PostMapping(value = "/getCarouselList")
    public Result<List<Carousel>> getCarouselList() {
        List<Carousel> carouselList = carouselService.list(new QueryWrapper<Carousel>().orderByAsc("c_order"));
        return Result.success(carouselList);
    }


    /**
     * 查询最热3作品和前5个最新作品
     */
    @PostMapping(value = "/getHotAndLasterDesignList")
    public Result<Map<String, List<ProjectDesignResponseVo>>> getHotAndLasterDesignList() {

        final IPage<ProjectDesignResponseVo> hotProjectDesignPage = new Page<>(1, 3);
        ProjectDesignRequestVo hotPdrv = ProjectDesignRequestVo.builder().isHot(1)
                .orderType("DESC").build();
        IPage<ProjectDesignResponseVo> hotPage = projectDesignMapper.getDesignList(hotProjectDesignPage, hotPdrv);

        final IPage<ProjectDesignResponseVo> noHotProjectDesignPage = new Page<>(1, 5);
        ProjectDesignRequestVo noHotPdrv = ProjectDesignRequestVo.builder().isHot(0)
                .orderType("DESC").build();
        IPage<ProjectDesignResponseVo> noHotPage = projectDesignMapper.getDesignList(noHotProjectDesignPage, noHotPdrv);

        Map<String, List<ProjectDesignResponseVo>> resultMap = new HashMap<>();
        resultMap.put("hot", hotPage.getRecords());
        resultMap.put("nomarl", noHotPage.getRecords());

        return Result.success(resultMap);
    }


    /**
     * 滚动查询最新设计
     */
    @PostMapping(value = "/getLasterDesignPage")
    public Result<IPage<ProjectDesignResponseVo>> getLasterDesignPage(@RequestBody ProjectDesignRequestVo projectDesignRequestVo) {

        final IPage<ProjectDesignResponseVo> noHotProjectDesignPage = new Page<>(projectDesignRequestVo.getPageNow(), projectDesignRequestVo.getPageSize());
        ProjectDesignRequestVo noHotPdrv = ProjectDesignRequestVo.builder().isHot(0)
                .orderType("DESC").build();
        IPage<ProjectDesignResponseVo> noHotPage = projectDesignMapper.getDesignList(noHotProjectDesignPage, noHotPdrv);

        return Result.success(noHotPage);
    }

    /**
     * 查询设计详情
     */
    @PostMapping(value = "/getDesignInfo")
    public Result<ProjectDesign> getDesignInfo(Integer projectId) {
        ProjectDesign projectDesign = projectDesignService.getOne(new QueryWrapper<ProjectDesign>().eq("project_id", projectId));
        if (nonNull(projectDesign)) {
            projectDesign.setDownloadUrl(StringUtils.EMPTY);
        }
        return Result.success(projectDesign);
    }


    /**
     * 详情兑换
     */
    @PostMapping(value = "/designExchange")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> designExchange(@Validated @NotNull Long projectId) {
        ShiroUser user = ShiroKit.getUser();
        if (isNull(user)) {
            throw new IllegalArgumentException("用户不应该为空,因为只有认证的用户才能进来访问该接口");
        }
        ExchangeDesign exchangeDesign = exchangeDesignService.getOne(new QueryWrapper<ExchangeDesign>()
                .eq("user_id", user.getId())
                .eq("project_id", projectId));
        // 判断是否兑换
        if (nonNull(exchangeDesign)) {
            //已经兑换了
            throw new IllegalArgumentException("您已经兑换");
        } else {
            //没有兑换
            ProjectDesign projectDesign = projectDesignService.getOne(new QueryWrapper<ProjectDesign>().eq("project_id", projectId));
            if (nonNull(projectDesign)) {
                WxUser wxUser = wxUserService.getOne(new QueryWrapper<WxUser>().eq("user_id", user.getId()));
                if (nonNull(wxUser)) {
                    if (wxUser.getIntegral() > 0 && wxUser.getIntegral() > projectDesign.getIntegration()) {
                        // 减去积分
                        long otherIntegra = wxUser.getIntegral() - projectDesign.getIntegration();
                        wxUser.setIntegral(otherIntegra);
                        wxUserService.update(wxUser, null);
                        // 添加兑换记录
                        ExchangeDesign newExchangeDesign = ExchangeDesign.builder()
                                .projectId(projectId)
                                .userId(Long.parseLong(wxUser.getUserId()))
                                .build();
                        exchangeDesignService.save(newExchangeDesign);
                        return Result.success("兑换成功");

                    } else {
                        throw new IllegalArgumentException("积分不足");
                    }
                } else {
                    throw new IllegalArgumentException("当前用户不存在");
                }
            } else {
                throw new IllegalArgumentException("设计居然不存在");
            }
        }
    }

    /**
     * 详情分享
     */
    @PostMapping(value = "/designShare")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> designShare(Integer projectId) {
        ProjectDesign projectDesign = projectDesignService.getOne(new QueryWrapper<ProjectDesign>().eq("project_id", projectId));
        if (nonNull(projectDesign)) {
            int i = projectDesign.getShareNum() + 1;
            projectDesign.setShareNum(i);
            projectDesignService.update(projectDesign, null);
            return Result.success("分享成功");
        }
        return Result.success("兑换失败");
    }


}
