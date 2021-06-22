package com.codebaobao.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    /**
     * 渲染返回 token 页面,因为前端页面接收的都是Result对象，故使用application/json返回
     *
     * @param response
     * @throws IOException
     */
    public static void renderToken(final HttpServletRequest request,final HttpServletResponse response, final Object obj) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://220.170.199.46:10011");
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9090");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers", "authorization,Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
        response.setHeader("Access-Control-Request-Headers","Origin, X-Requested-With, content-Type, Accept, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        final ServletOutputStream out = response.getOutputStream();
        out.write(JSONObject.toJSONString(obj).getBytes("UTF-8"));
        out.flush();
        out.close();
    }


}
