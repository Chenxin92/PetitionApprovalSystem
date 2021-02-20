package com.dfrz.javaprojectstage3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Notice;
import com.dfrz.javaprojectstage3.bean.Role;
import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.service.IUserService;
import com.dfrz.javaprojectstage3.service.NoticeService;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
/**
 * 作者：zhengyefeng
 * 日期: 2021/2/20 0:53
 * 描述:
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    /*跳转树形菜单页面*/
    @RequestMapping("/tree")
    public ModelAndView toTree(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("tree");
        return mv;
    }
}
