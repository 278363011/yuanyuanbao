package com.yuanbaobao.mini.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanbaobao.mini.constants.Constant;
import com.yuanbaobao.mini.exception.WxApiException;
import com.yuanbaobao.mini.mapper.WxUserMapper;
import com.yuanbaobao.mini.model.WxUser;
import com.yuanbaobao.mini.service.WxUserService;
import com.yuanbaobao.mini.shiro.token.WxToken;
import com.yuanbaobao.mini.utils.UUIDUtil;
import com.yuanbaobao.mini.vo.WxLoginRequestVo;
import com.yuanbaobao.mini.vo.WxLoginResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author codebaobao
 * @since 2021-07-03
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {
    @Value("${app.id}")
    private String appid;

    @Value("${app.secret}")
    private String appSecret;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WxUserService wxUserService;

    /**
     * 微信小程序整个认证流程
     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
     */
    @Override
    public Map<String, String> login(WxLoginRequestVo wxLoginRequestVo) throws Exception {
        String wxUserInfo = getUserInfoFormWxServer(wxLoginRequestVo.getCode());
        WxLoginResponseVo wxResponse = JSONObject.toJavaObject(JSONObject.parseObject(wxUserInfo), WxLoginResponseVo.class);
        if (!StringUtils.equalsIgnoreCase(wxResponse.getErrcode(), "0")) {
            throw new WxApiException("请求微信api失败 : " + wxResponse.getErrmsg());
        }
        WxUser wxUser = wxUserService.getOne(
                new QueryWrapper<WxUser>().eq("open_id", wxResponse.getOpenid())
        );
        String sessionKey = wxResponse.getSession_key();
        //不存在就新建用户
        if (isNull(wxUser)) {
            wxUser = WxUser.builder()
                    .avatarUrl(wxLoginRequestVo.getAvaUrl())
                    .city(wxLoginRequestVo.getCity())
                    .country(wxLoginRequestVo.getCountry())
                    .wxLanguage(wxLoginRequestVo.getLanguage())
                    .openId(wxResponse.getOpenid())
                    .userId(UUIDUtil.getUUID())
                    .province(wxLoginRequestVo.getProvince())
                    .gender(wxLoginRequestVo.getSex())
                    .nickName(wxLoginRequestVo.getNick())
                    .sessionKey(sessionKey)
                    .build();
        }
        // 更新sessionKey和 登陆时间
        else {
            wxUser.setSessionKey(sessionKey);
        }
        wxUserService.saveOrUpdate(wxUser);

        // shiro登录
        Subject subject = SecurityUtils.getSubject();
        WxToken wxToken = new WxToken(wxUser);
        subject.login(wxToken);

        // 封装返回小程序
        Map<String, String> result = new HashMap<>();
        result.put("token", subject.getSession().getId().toString());
        result.put("userId", wxUser.getUserId());
        return result;
    }


    /**
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     */
    private String getUserInfoFormWxServer(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("appid", appid);
        params.add("secret", appSecret);
        params.add("js_code", code);
        params.add("grant_type", "authorization_code");
        URI code2Session = getURIwithParams(Constant.WX_SERVER_CODE_URL, params);
        return restTemplate.exchange(code2Session, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
    }

    private URI getURIwithParams(String url, MultiValueMap<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParams(params);
        return builder.build().encode().toUri();
    }

}
