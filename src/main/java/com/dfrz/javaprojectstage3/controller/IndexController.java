package com.dfrz.javaprojectstage3.controller;
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
    private static Logger logger= LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/toIndex")
    public ModelAndView toIndex(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
