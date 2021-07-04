//package com.yuanbaobao.mini.filter;
//import com.yuanbaobao.mini.result.CodeMsg;
//import com.yuanbaobao.mini.result.Result;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//public class AuthenticationJailer implements Filter {
//    private JwtTokenUtil jwtTokenUtil;
//    public AuthenticationJailer(JwtTokenUtil jwtTokenUtil){
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    public static Set<String> ignorePath;
//    static {
//        ignorePath = new HashSet<>();
//        ignorePath.add("/admin/login");
//        ignorePath.add("/admin/authentication");
//        ignorePath.add("/h5/student/login");
//        ignorePath.add("/h5/student/register");
//        ignorePath.add("/h5/home/getHomeSchoolArea");
//        ignorePath.add("/h5");
//        ignorePath.add("/h5/");
//        ignorePath.add("/h5/index");
//        ignorePath.add("/favicon.ico");
//        ignorePath.add("/");
//        ignorePath.add("/index");
//        ignorePath.add("/api/singleFile");
//        ignorePath.add("/singleFile");
//        ignorePath.add("/h5/home/sendSms");
//        ignorePath.add("/h5/home/checkNewPwd");
//    }
//
//
//    @Override
//    public void init(final FilterConfig filterConfig) throws ServletException {
//        // do nothing
//    }
//    @Override
//    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws IOException, ServletException {
//            final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//            final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//            final String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "");
////        System.out.println(path);
//            if(new AntPathMatcher().match("/static/**", path) || new AntPathMatcher().match("/images/**", path)){
//                chain.doFilter(servletRequest, servletResponse);
//                return;
//            }
//            if (ignorePath.contains(path)) {
//                chain.doFilter(servletRequest, servletResponse);
//                return;
//            }else {
//                final String authHeader = httpRequest.getHeader(this.jwtTokenUtil.getHeader());
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    final String authToken = authHeader.substring("Bearer ".length());
//                    if (!this.jwtTokenUtil.isTokenExpired(authToken)) {
//                        chain.doFilter(servletRequest, servletResponse);
//                    } else {
//                        com.codebaobao.utils.ResponseUtils.renderToken(httpRequest,httpResponse, Result.error(CodeMsg.create(8843, "token过期!")));
//                    }
//                } else {
//                    com.codebaobao.utils.ResponseUtils.renderToken(httpRequest,httpResponse, Result.error(CodeMsg.create(10002, "请输入token!")));
//                    return;
//                }
//            }
//        }
//
//        @Override
//        public void destroy () {
//            //do nothing
//        }
//}