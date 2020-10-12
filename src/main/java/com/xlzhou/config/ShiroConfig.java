package com.xlzhou.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: zxl
 * @Date: 2020/9/8 - 09 - 08 - 20:58
 */
@Configuration
public class ShiroConfig {



    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        /**
         * 添加内置过滤器
         * anno 无须认证就能访问
         * authc必须认证才能访问
         * user 必须拥有记住我功能才能使用
         * perms 拥有对某个资源的权限才能访问
         * role 拥有某个角色权限才能访问
         *
         *   map.put("/user/add","authc");
         *   map.put("/user/update","authc");
         *
         */
        //拦截
        Map<String, String> map = new LinkedHashMap<>();
        //授权认证 正常情况下 没有授权会跳到未授权页面  user:add为授权标签，资源权限名称
        map.put("/user/add","perms[user:add]");
        map.put("/user/update","perms[user:update]");
        //对user文件夹下的页面必须认证才能访问
        map.put("/user/*","authc");


        bean.setFilterChainDefinitionMap(map);
        //设置登录请求  没有权限跳转到登录界面
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");


        return bean;
    }


    //Qualifier指定方法名关联
    @Bean(name="getDefaultSecurityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //创建Realm对象  需要自定义
    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        // 配置 加密 （在加密后，不配置的话会导致登陆密码失败）  将加密算法存到Realm对象中
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return  userRealm;
    }

    //shiro配置加密算法
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用md5 算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置散列次数： 意为加密几次
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    //shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
