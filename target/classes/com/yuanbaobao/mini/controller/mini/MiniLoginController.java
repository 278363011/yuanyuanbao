package com.yuanbaobao.mini.controller.mini;


import com.yuanbaobao.mini.result.Result;
import com.yuanbaobao.mini.service.WxUserService;
import com.yuanbaobao.mini.shiro.factory.ShiroFactroy;
import com.yuanbaobao.mini.shiro.utils.ShiroKit;
import com.yuanbaobao.mini.vo.WxLoginRequestVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/mini/wx/user")
public class MiniLoginController {

    @Autowired
    WxUserService wxUserService;

    @PostMapping(value = "/user/sign_in")
    public Result<Map<String, String>> login(@Validated @NotNull @RequestBody WxLoginRequestVo wxLoginRequestVo) throws Exception {
        Map<String, String> resultMap = wxUserService.login(wxLoginRequestVo);
        if (resultMap.size() != 2) {
            throw new IllegalStateException("生成token异常");
        }
        return Result.success(resultMap);
    }

    @RequestMapping("/test")
    public Result<String> test() {
        System.out.println(ShiroKit.getSession().getId());
        ShiroKit.getSession().setAttribute("a", "b");

        return Result.success("测试成功1");
    }

    @RequestMapping("/test2")
    public Result<String> test2() {
        System.out.println(ShiroKit.getSession().getId());
        System.out.println(ShiroKit.getSession().getAttribute("a"));

        return Result.success("权限成功2");
    }

    @RequiresPermissions("/caidan1")
    @RequestMapping("/test3")
    public Result<String> test3() {
        return Result.success("测试成功3");
    }


    @RequestMapping("/showOnLine")
    public Result<Collection<Session>> showOnLine() {
        return Result.success(ShiroFactroy.getCurrentAllSessions());
    }

    @RequestMapping("/offLine")
    public Result<String> offLine(String key) {
        ShiroFactroy.deleteCache("admin", true);
        return Result.success("测试成功3");
    }

    //    /**
//     * 退出登录
//     * @return
//     */
//    @RequestMapping("/logout")
//    public Result<String> logout(){
//        SecurityUtils.getSubject().logout();
//        return Result.success("退出成功");
//    }


}
