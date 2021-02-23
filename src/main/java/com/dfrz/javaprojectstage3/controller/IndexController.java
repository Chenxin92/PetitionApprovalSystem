package com.dfrz.javaprojectstage3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dfrz.javaprojectstage3.bean.Count;
import com.dfrz.javaprojectstage3.bean.Notice;
import com.dfrz.javaprojectstage3.service.IUserService;
import com.dfrz.javaprojectstage3.service.NoticeService;
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
    @Autowired
    NoticeService noticeService;

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/toIndex")
    public ModelAndView toIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        List<Notice> noticeList = noticeService.getNotice();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        mv.addObject("noticeList", noticeList);
        return mv;
    }

    /**
     * 部门人数统计表格
     *
     * @return
     */
    @RequestMapping("/departmentCount")
    public String getDepartmentCount() {
        //获取部门名称
        //List<String> departmentList = userService.getDepartmentName();
        //获取各部门人数
        //List datas = userService.getgetDepartmentCount();
        //Map<String, Object> maps = new HashMap<>();
        //maps.put("departmentname", departmentList);
        //maps.put("datas", datas);
        //Result result = ResultUtils.success(maps);
        List<Count> countList = userService.getCountList();
        return JSON.toJSONString(countList);
    }
}
