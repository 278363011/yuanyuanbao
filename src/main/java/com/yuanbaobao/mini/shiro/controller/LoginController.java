package com.yuanbaobao.mini.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sys")
public class LoginController {

//    @GetMapping("/adminLogin")
//    public Result<String> login(UserVo user) throws Exception {
//
//        if (StringUtils.isEmpty(user.getUsrname()) || StringUtils.isEmpty(user.getPwd())) {
//            throw new NullPointerException("账户密码不能为空");
//        }
//        //用户认证信息
//        Subject subject = SecurityUtils.getSubject();
//        com.codebaobao.shiro.token.AdminToken admintoken = new com.codebaobao.shiro.token.AdminToken();
//        admintoken.setUsername(user.getUsrname());
//        admintoken.setPassword(user.getPwd().toCharArray());
//        //进行验证，这里的异常全抛
//        subject.login(admintoken);
//
//        return Result.success("" + ShiroKit.getSession().getId());
//
//
//    }

//    @PostMapping("/wxLogin")
//    public Result wxLogin(@RequestParam("code") String code,
//                          @RequestParam("nickName") String nickName,
//                          @RequestParam("avatarUrl") String avatarUrl,
//                          @RequestParam("gender") String gender) {
//        //发送请求完成登录
//        Map<String, String> param = new HashMap<>();
//        param.put("appid", Constant.WX_LOGIN_APPID);
//        param.put("secret", Constant.WX_LOGIN_SECRET);
//        param.put("js_code", code);
//        param.put("grant_type", Constant.WX_LOGIN_GRANT_TYPE);
//        String wxResult = HttpClientUtil.doGet(Constant.WX_LOGIN_URL, param);
//        JSONObject jsonObject = JSONObject.parseObject(wxResult);
////        String session_key = jsonObject.get("session_key").toString();
//        String open_id = jsonObject.get("openid").toString();
//
//        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
//        Subject subject = SecurityUtils.getSubject();
//        User user = new User();
//        user.setAvatarUrl(avatarUrl);
//        user.setNickName(nickName);
//        if (gender.equals("1"))
//            user.setGender("男");
//        else
//            user.setGender("女");
//        user.setLastLogin(new Date());
//        user.setOpenId(open_id);
//        WxToken wxToken = new WxToken(user);
//        subject.login(wxToken);
//
//        // 封装返回小程序
//        Map<String, String> result = new HashMap<>();
//        result.put("token", subject.getSession().getId().toString());
//
//        return new Result(result);
//    }


}
