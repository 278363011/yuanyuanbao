//package com.codebaobao.shiro.controller;
//
//
//import com.codebaobao.result.CodeMsg;
//import com.codebaobao.result.Result;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequestMapping("/common")
//public class CommonController {
//    /**
//     * 未授权跳转方法
//     * @return
//     */
//    @RequestMapping("/unauth")
//    public Result<String> unauth(){
//        SecurityUtils.getSubject().logout();
//        return Result.error(CodeMsg.create(1001,"您不具有该权限"));
//    }
//
//    /**
//     * 被踢出后跳转方法
//     * @return
//     */
//    @RequestMapping("/kickout")
//    public Result<String> kickout(){
//        return Result.error(CodeMsg.create(1002,"当前用户已在异地登录，请确认是本人"));
//    }
//
//    /**
//     * 被踢出后跳转方法
//     * @return
//     */
//    @RequestMapping("/index")
//    public Result<String> index(){
//        return Result.success("登录成功");
//    }
//
//
//}
