package com.dfrz.javaprojectstage3.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Notice;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.service.IPetitionService;
import com.dfrz.javaprojectstage3.service.NoticeService;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 公告
 * @author Chenxin
 * @date 2021/02/17 1:09
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    private static Logger logger = LoggerFactory.getLogger(NoticeController.class);
    /**
     * 公告列表页面数据
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/noticelistAjax")
    public Result getList1(Integer page, Integer limit) {
        // List<User> list=userService.getUsers();
        //分页
        Page p = new Page();
        p.setCurrent(page);
        p.setSize(limit);

        IPage iPage = noticeService.getNoticeByPage(p);
        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
    }
    /**
     * 详细公告页面
     * @return
     */
    @RequestMapping("/tonoticedetails")
    public ModelAndView tonoticeDetails(Integer id) {
        ModelAndView mv = new ModelAndView();
        //获取数据
        Notice notice=noticeService.getNoticeById(id);
        mv.setViewName("noticedetails");
        mv.addObject("notice", notice);
        return mv;
    }
    /**
     * 添加公告页面
     * @return
     */
    @RequestMapping("/tonoticeadd")
    public ModelAndView toNoticeadd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("noticeadd");
        return mv;
    }

    /**
     * 公告列表页面
     * @return
     */
    @RequestMapping("/tonotice")
    public ModelAndView notice() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("noticelist");
        return mv;
    }


}