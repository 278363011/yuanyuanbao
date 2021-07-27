package com.yuanbaobao.mini.controller.mini;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuanbaobao.mini.mapper.ProjectDesignMapper;
import com.yuanbaobao.mini.result.Result;
import com.yuanbaobao.mini.vo.ProjectDesignRequestVo;
import com.yuanbaobao.mini.vo.ProjectDesignResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mini/wx/select")
public class SelectedController {

    @Autowired
    ProjectDesignMapper projectDesignMapper;

    @PostMapping(value = "/getCarouselList")
    public Result<IPage<ProjectDesignResponseVo>> getHotAndLasterDesignList() {
        final IPage<ProjectDesignResponseVo> hotProjectDesignPage = new Page<>(1, 3);
        ProjectDesignRequestVo hotPdrv = ProjectDesignRequestVo.builder().isHot(1)
                .cateType(1)
                .titleContent("2")
                .orderType("DESC").build();
        IPage<ProjectDesignResponseVo> selectedDesignList = projectDesignMapper.getSelectedDesignList(hotProjectDesignPage, hotPdrv);
        return Result.success(selectedDesignList);
    }
}
