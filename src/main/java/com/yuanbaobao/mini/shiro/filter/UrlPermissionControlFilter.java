package com.yuanbaobao.mini.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.yuanbaobao.mini.model.SysApi;
import com.yuanbaobao.mini.result.CodeMsg;
import com.yuanbaobao.mini.result.Result;
import com.yuanbaobao.mini.shiro.model.ShiroUser;
import com.yuanbaobao.mini.shiro.utils.ShiroKit;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UrlPermissionControlFilter extends PathMatchingFilter {


    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        //请求的url
        String requestURL = getPathWithinApplication(request);
        System.out.println("请求的url :" + requestURL);
        Subject subject = ShiroKit.getSubject();
        ;
        if (!subject.isAuthenticated()) {
            // 如果没有登录, 直接返回true 进入登录流程
            return true;
        }
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();

        SysApi sysApia = new SysApi();
        sysApia.setUrl("/sys/test3");
        Set<SysApi> asd = new HashSet<>();
        asd.add(sysApia);
        shiroUser.setApiPermissions(asd);

        boolean hasPermission = false;
        if (Objects.isNull(shiroUser.getApiPermissions())) {
            UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径" + requestURL + "的权限");
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(200);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println(JSONObject.toJSON(Result.error(CodeMsg.create(10005, ex.getMessage()))));
            out.flush();
            out.close();
            return false;
        }


        for (SysApi sysApi : shiroUser.getApiPermissions()) {

            if (sysApi.getUrl().equals(requestURL)) {
                hasPermission = true;
                break;
            }
        }
        if (hasPermission) {
            return true;
        } else {

            UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径" + requestURL + "的权限");
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(200);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println(JSONObject.toJSON(Result.error(CodeMsg.create(10005, ex.getMessage()))));
            out.flush();
            out.close();
            return false;
        }

    }
}
