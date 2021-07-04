package com.yuanbaobao.mini.shiro.filter;

import com.yuanbaobao.mini.shiro.model.ShiroUser;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class UserPermissionFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接返回
            return false;
        }
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        PatternMatcher matcher = new AntPathMatcher();
        HttpServletRequest httpRequest = (HttpServletRequest) request;


        if (shiroUser.getApiPermissions() != null) {
            return shiroUser.getApiPermissions().stream().anyMatch(item -> {
                return matcher.matches(item.getUrl(), httpRequest.getRequestURI());
            });
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        //返回一点东西

        return false;
    }
}
