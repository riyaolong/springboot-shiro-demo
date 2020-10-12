package com.xlzhou.controller;

import com.xlzhou.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zxl
 * @Date: 2020/9/8 - 09 - 08 - 20:47
 */

@Controller
public class MyController {

    @RequestMapping({"/index"})
    public String toIndex(Model model){
        model.addAttribute("message","Hello,Shiro!");
        return "index";
    }


    @RequestMapping("/user/add")
    public String add(){
        return "add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "update";
    }


    @RequestMapping({"/","/toLogin"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping({"/login"})
    public String login(String username ,String password,Model model){
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录信息  令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        token.setRememberMe(true); //设置记住我
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) { //用户名不存在
            model.addAttribute("message","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){//密码错误
            model.addAttribute("message","密码错误");
            return "login";
        }
    }


    @RequestMapping("/noauth")
    @ResponseBody  //将方法的返回值，以特定的格式写入到response的body区域，进而将数据返回给客户端。
    public String unauthorized(){
        return "未经授权无法访问";

    }

    @RequestMapping("/logout")
    public String logout(){
        //登出清除缓存
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }
}
