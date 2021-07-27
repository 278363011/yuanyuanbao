package com.yuanbaobao.mini.shiro.config;


import com.yuanbaobao.mini.shiro.filter.SessionControlFilter;
import com.yuanbaobao.mini.shiro.filter.UrlPermissionControlFilter;
import com.yuanbaobao.mini.shiro.manager.CustomSessionManager;
import com.yuanbaobao.mini.shiro.modular.CustomModularRealmAuthenticator;
import com.yuanbaobao.mini.shiro.realm.AdminRealm;
import com.yuanbaobao.mini.shiro.realm.WxRealm;
import com.yuanbaobao.mini.shiro.utils.ShiroKit;
import lombok.Data;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Configuration
@ConfigurationProperties(
        prefix = "spring.redis"
)
@Data
public class ShiroConfig {

    private String host = "localhost";
    private int port = 6379;
    private String password;
    private Duration timeout;
    private int database;

    private final String SESSION_KEY = "shiro:session:";


    /**
     * Shiro的过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        // 配置ShiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();

        // 设置security manager
        shiroFilter.setSecurityManager(securityManager);

        // 登录跳转: 如果你没有登录则会跳到这个页面中 - 如果没有设置值则会默认跳转到工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilter.setLoginUrl("/sys/login");

        // 登录后跳转 （这里没用，前端vue控制了跳转）
        shiroFilter.setSuccessUrl("/common/index");

        // 未授权跳转
        shiroFilter.setUnauthorizedUrl("/common/unauth");

        /**
         * 自定义拦截器
         **/
        Map<String, Filter> customFilters = new LinkedHashMap<>();
        //限制同一帐号同时在线的个数。
        customFilters.put("kickout", kickoutSessionControlFilter());
        //用户的权限控制
        customFilters.put("requestURL", urlPermissionControlFilter());
        //设置自定义过滤器
        shiroFilter.setFilters(customFilters);

        /* 配置映射关系*/
        /**
         * 配置shiro拦截器链
         * 从上向下顺序执行，authc 应放在 anon 下面
         * anon  不需要认证 可以匿名访问
         * authc 需要认证  认证通过才可以访问
         * custom  验证通过或RememberMe登录的都可以
         * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
         * 顺序从上到下,优先级依次降低
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        //登录
        filterChainDefinitionMap.put("/**", "anon");
//        //验证码
//        filterChainDefinitionMap.put("/kaptcha", "anon");
//        //测试1
//        filterChainDefinitionMap.put("/mini/wx/test", "anon");
//        //测试2
//        filterChainDefinitionMap.put("/mini/wx/test2", "authc");
//        //测试3
//        filterChainDefinitionMap.put("/mini/wx/test3", "authc");

        // 所有url都必须认证通过才可以访问
//        filterChainDefinitionMap.put("/**", "kickout");

        //下面的配置路径 都需要在上面配置 authc 否则访问不到filter
//        filterChainDefinitionMap.put("/mini/wx/test2", "requestURL");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

    /**
     * 安全管理器 整个以后所有=======================================================
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义rememberme 管理 coookie
        securityManager.setRememberMeManager(rememberMeManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 定义多个realms
        List<Realm> list = new ArrayList<>();
        list.add(adminRealm());
        list.add(wxCodeRealm());
        // 多个realms 认证策略
        securityManager.setAuthenticator(modularRealmAuthenticator());
        // 设置多个realms
        securityManager.setRealms(list);
        return securityManager;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        CustomModularRealmAuthenticator modularRealmAuthenticator = new CustomModularRealmAuthenticator();
        //只要有一个成功就视为登录成功
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }


    /**
     * 自定义的Realm==================================================
     */

    @Bean
    public AdminRealm adminRealm() {
        AdminRealm adminRealm = new AdminRealm();
        //账号密码登录使用realm
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminRealm;
    }

    @Bean
    public WxRealm wxCodeRealm() {
        //小程序使用openid登录使用的realm
        return new WxRealm();
    }


    /**
     * extends SimpleCredentialsMatcher 自定义密码匹配规则
     * @Bean
     *     public CredentialsMatcher credentialsMatcher() {
     *         return new CredentialsMatcher();
     *     }
     */

    /**
     * 设置加密方式matcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        return hashedCredentialsMatcher;
    }

    /**
     * 分布式session+redis 整合 ===============================================================
     *
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        redisSessionDAO.setKeyPrefix(SESSION_KEY);
        redisSessionDAO.setExpire(1800);
        return redisSessionDAO;
    }

    /**
     * redis 配置
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setDatabase(database);
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout((int) timeout.toMillis());
//        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * sessionId生成器
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * session管理器
     */
    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager mySessionManager = new CustomSessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

    /**
     * redis + 缓存 整合管理器
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        // 必须要设置主键名称，shiro-redis 插件用过这个缓存用户信息
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    /**
     * 自定义人数session控制拦截器  限制同一账号登录同时登录人数控制
     */
    @Bean
    public SessionControlFilter kickoutSessionControlFilter() {
        SessionControlFilter kickoutSessionControlFilter = new SessionControlFilter();
        kickoutSessionControlFilter.setCache(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/common/kickout");
        return kickoutSessionControlFilter;
    }

    /**
     * 自定义 permission url path matcher 拦截器
     *
     * @return
     */
    public UrlPermissionControlFilter urlPermissionControlFilter() {
        return new UrlPermissionControlFilter();
    }


    /**
     * ===============================================================
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
        manager.setCookie(rememberMeCookie());
        return manager;
    }

    /**
     * 记住密码Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("SHARE_JSESSIONID");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
        //  path为 / 用于多个系统共享 JSESSIONID
        simpleCookie.setPath("/");
        return simpleCookie;
    }


    /**
     * 在方法中 注入 securityManager,进行代理控制===============================================================
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new Object[]{securityManager()});
        return bean;
    }

    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     * 此方法需要用static作为修饰词，否则无法通过@Value()注解的方式获取配置文件的值
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     * * 使授权注解起作用不如不想配置可以在pom文件中加入
     * * <dependency>
     * *<groupId>org.springframework.boot</groupId>
     * *<artifactId>spring-boot-starter-aop</artifactId>
     * *</dependency>
     * * @param securityManager
     * * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


}
