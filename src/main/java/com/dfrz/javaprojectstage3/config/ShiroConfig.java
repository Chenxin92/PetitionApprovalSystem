package com.dfrz.javaprojectstage3.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.dfrz.javaprojectstage3.realm.UserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*Shiro的配置类*/
@Configuration
public class ShiroConfig {
    // 创建自定义 realm
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    // 创建 SecurityManager 对象
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    // Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         */
        Map<String, String> map = new LinkedHashMap<>();

        // 静态资源放行
        map.put("/static/**", "anon");
        map.put("/css/**", "anon");
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");
        map.put("/lib/**", "anon");

        // 转向登录页面
        map.put("/user/toLogin", "anon");
        // 登陆动作
        map.put("/user/login", "anon");
        // 对所有用户认证
        map.put("/**", "authc");

        // 登录
        // 注意：这里配置的 /login 是指到 @RequestMapping(value="/login")中的 /login
        shiroFilterFactoryBean.setLoginUrl("/user/toLogin");
        // 首页
        shiroFilterFactoryBean.setSuccessUrl("/toIndex");
        // 错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    // 加入注解的使用，不加这个，注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    // 跟上面的注解配置搭配使用，有时候加了上面的配置后注解不生效，需要加入下面的配置
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;
    }

    /*运行在模板中使用shiro的标签*/
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {

        return new ShiroDialect();

    }
}

