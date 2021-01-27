package com.dfrz.javaprojectstage3.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 作者：zhengyefeng
 * 日期: 2021/1/26 14:36
 * 描述:
 */
@RestController

public class Usercontroller {
    private static Logger logger= LoggerFactory.getLogger(Usercontroller.class);
    @Autowired
    IUserService userService;


    /*
登录页面
 */
    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        return mv;
    }


/*
登录方法
 */
@RequestMapping("/login")
public Map<String,String> login(User user){
    logger.info("execute login method. Login uname="+user.getUsername()+",upass="+user.getPassword());
       /* boolean flag=userService.login(user);
        logger.info("login flag="+flag);
        Map<String,String> map=new HashMap<>();
        map.put("flag",flag+"");
        return map;*/
    Map<String,String> map=new HashMap<>();
    // 1.获取Subject
    Subject subject = SecurityUtils.getSubject();
    Session session=subject.getSession();
    // 2.封装用户数据
    UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
    // 3. 执行shiro的login方法--会自动调用Realm中的认证方法doGetAuthenticationInfo
    try{
        subject.login(token);
        //登陆成功后，将用户的学生或讲师信息保存至session中
        User currentUser=(User)subject.getPrincipal();


        session.setAttribute("currentUser",user);

        map.put("flag","1");
    }
    catch (UnknownAccountException e){
        logger.info("没有此账号");
        map.put("flag","-1");
    }
    catch (IncorrectCredentialsException e){
        logger.info("密码不正确");
        map.put("flag","-2");
    }
    catch (AuthenticationException e){
        logger.info("认证失败");
        map.put("flag","-3");
    }
    return map;
}
}
