package com.yuanbaobao.mini.shiro.realm;

import com.yuanbaobao.mini.model.WxUser;
import com.yuanbaobao.mini.shiro.factory.IShiro;
import com.yuanbaobao.mini.shiro.factory.ShiroFactroy;
import com.yuanbaobao.mini.shiro.model.ShiroUser;
import com.yuanbaobao.mini.shiro.token.WxToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

import static java.util.Objects.nonNull;

public class WxRealm extends AuthenticatingRealm {

    /**
     * 鉴权   openid 判断是否用户是否已经绑定微信
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        WxToken wxToken = (WxToken) token;
        WxUser user = wxToken.getUser();

        //获取shiro user 处理工厂类
        IShiro shiroFactory = ShiroFactroy.me();
        //转换为ShiroUser
        ShiroUser shiroUser = shiroFactory.shiroWxUser(user);

        return new SimpleAuthenticationInfo(shiroUser, user, this.getClass().getSimpleName());
    }


    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //设置此Realm支持的Token
        return nonNull(authenticationToken) && (authenticationToken instanceof WxToken);
    }

    @Override
    public String getName() {
        return "WxRealm";
    }

}
