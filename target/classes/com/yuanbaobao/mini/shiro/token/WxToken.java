package com.yuanbaobao.mini.shiro.token;

import com.yuanbaobao.mini.model.WxUser;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class WxToken extends UsernamePasswordToken implements Serializable {
    private static final long serialVersionUID = 4812793519945855483L;

    private WxUser wxUser;

    @Override
    public Object getPrincipal() {
        return getOpenId();
    }

    @Override
    public Object getCredentials() {
        return wxUser;
    }

    public WxToken(WxUser wxUser) {
        this.wxUser = wxUser;
    }

    public String getOpenId() {
        return wxUser.getOpenId();
    }

    public WxUser getUser() {
        return wxUser;
    }
}
