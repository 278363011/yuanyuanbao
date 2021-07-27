//package com.yuanbaobao.mini.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class GlobalCorsConfig implements WebMvcConfigurer {
//
//    @Value("${virtual.images.path}")
//    String virtualImagePath;
//
//    @Override
//    public void addCorsMappings(final CorsRegistry registry) {
//        // 设置允许跨域的路径
//        registry.addMapping("/**")
//                // 设置允许跨域请求的域名
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                // 是否允许证书 不再默认开启
//                .allowCredentials(true)
//                // 设置允许的方法
//                .allowedMethods("*")
//                // 跨域允许时间
//                .maxAge(10000);
//    }
//
//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        /*
//         * 虚拟路径
//         */
//        registry.addResourceHandler("/images/**").addResourceLocations("file:" + this.virtualImagePath);
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }
//}
