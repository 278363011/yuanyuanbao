package com.yuanbaobao.mini.controller.admin;


import com.yuanbaobao.mini.result.Result;
import com.yuanbaobao.mini.service.SysUserService;
import com.yuanbaobao.mini.vo.AdminLoginRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-04
 */
@RestController
@RequestMapping("/admin")
public class SysUserLoginController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "/user/login")
    public Result<Map<String, String>> login(@Validated @NotNull @RequestBody AdminLoginRequestVo adminLoginRequestVo) throws Exception {
        Map<String, String> resultMap = sysUserService.login(adminLoginRequestVo);
        if (resultMap.isEmpty()) {
            throw new IllegalStateException("生成token为空");
        }
        return Result.success(resultMap);
    }

}
