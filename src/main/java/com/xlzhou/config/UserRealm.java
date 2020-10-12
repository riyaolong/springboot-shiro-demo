package com.xlzhou.config;

import com.xlzhou.model.User;
import com.xlzhou.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: zxl
 * @Date: 2020/9/8 - 09 - 08 - 20:59
 */
//自定义Realm 继承AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 授权 操作 doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //对所有用户进行授权  授权的标签为user:add
//        info.addStringPermission("user:add");
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //拿到当前登录的用户信息  通过认证方法传递过来
        User currentUser = (User) subject.getPrincipal();
        //对当前用户授权  权限为currentUser.getAuth()
        info.addStringPermission(currentUser.getAuth());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了 认证 操作 doGetAuthorizationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接数据库  获取当前用户信息
        User user = userService.queryUserByName(userToken.getUsername());
        if (user==null) { //验证用户名 错误抛异常
            return null; //抛出用户名不存在异常
        }
        //前台传过来的密码
//      String password=userToken.getPassword().toString();
//        Object obj = new SimpleHash(
//                "MD5",//加密方式
//                "123",  //加密对象
//                ByteSource.Util.bytes("aaa"),   //盐值加密
//                1024  //设置散列次数： 意为加密几次
//        );
//        System.out.println(obj.toString());

        SimpleAuthenticationInfo simpleInfo = new SimpleAuthenticationInfo(
                user,    //当前用户信息  传递到授权方法
                user.getPassword(),   //密码
                ByteSource.Util.bytes(user.getUsername()), //对用户名进行盐值加密
                getName()  //认证名
        );
        return simpleInfo;
    }
}
