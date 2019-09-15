package com.goods.config;

import java.util.LinkedHashMap;
import java.util.Map;

import com.goods.shiro.CustomRealm;
import com.goods.shiro.RetryLimitHashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.cache.ehcache.EhCacheManager;


@Configuration
public class ShiroConfiguration {

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中：
     * 1、安全管理器：securityManager
     * 可见securityManager是整个shiro的核心；
     *
     * @return
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");
         //外部可以不需要登陆系统可直接访问的下载页面
        filterChainDefinitionMap.put("/js/**", "anon");
        //filterChainDefinitionMap.put("/jsp/**", "anon");
//        filterChainDefinitionMap.put("/manage/fileManage/getFileManageModelList", "anon");
//        filterChainDefinitionMap.put("/manage/download", "anon");
//        filterChainDefinitionMap.put("/transportMag/**", "anon");//运管数据访问权限


        //数据中心
//        filterChainDefinitionMap.put("/dump", "anon");
//        filterChainDefinitionMap.put("/health", "anon");
//        filterChainDefinitionMap.put("/info", "anon");
//        filterChainDefinitionMap.put("/loggers", "anon");
//        filterChainDefinitionMap.put("/features", "anon");
//        filterChainDefinitionMap.put("/beans", "anon");
//        filterChainDefinitionMap.put("/metrics/**", "anon");
//        filterChainDefinitionMap.put("/heapdump", "anon");
//        filterChainDefinitionMap.put("/env", "anon");
//        filterChainDefinitionMap.put("/auditevents", "anon");








        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        //shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(credentialsMatcher());
        customRealm.setCachingEnabled(true);
        customRealm.setAuthenticationCachingEnabled(true);
        customRealm.setAuthenticationCacheName("authenticationCache");
        customRealm.setAuthorizationCachingEnabled(true);
        customRealm.setAuthorizationCacheName("authorizationCache");
        return customRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public RetryLimitHashedCredentialsMatcher credentialsMatcher() {
        RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
        credentialsMatcher.setHashAlgorithmName("md5"); //md5进行加密
        credentialsMatcher.setHashIterations(2);//进行两次的迭代加密
        credentialsMatcher.setStoredCredentialsHexEncoded(true);//为true时使用Hex编码(默认)；false时使用Base64编码
        return credentialsMatcher;
    }

    //回话管理器
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000); //1800000
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
       // defaultWebSessionManager.setSessionValidationScheduler(sessionValidationScheduler());
        return defaultWebSessionManager;
    }
    //会话DAO
    @Bean
    public EnterpriseCacheSessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO EnterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        EnterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        EnterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return EnterpriseCacheSessionDAO;
    }

    //会话ID生成器
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        JavaUuidSessionIdGenerator javaUuidSessionIdGenerator = new JavaUuidSessionIdGenerator();
        return javaUuidSessionIdGenerator;
    }

}
