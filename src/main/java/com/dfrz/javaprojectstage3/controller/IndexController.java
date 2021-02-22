package com.dfrz.javaprojectstage3.controller;
import com.dfrz.javaprojectstage3.service.IUserService;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 作者：zhengyefeng
 * 日期: 2021/1/26 16:00
 * 描述:
 */
@RestController
public class IndexController {
    @Autowired
    IUserService userService;
    private static Logger logger= LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/toIndex")
    public ModelAndView toIndex(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/welcome")
    public ModelAndView welcome(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }

    /**
     * 部门人数统计表格
     * @return
     */
    @RequestMapping("/departmentcount")
    public Result getdepartmentcount(){
        //获取部门名称
        List<String> departmentname=userService.getDepartmentName();
        //获取各部门人数
        List datas=userService.getgetDepartmentCount();
        Map<String,Object> maps=new HashMap<>();
        maps.put("departmentname",departmentname);
        maps.put("datas",datas);
        Result result= ResultUtils.success(maps);
        return result;
    }
}
