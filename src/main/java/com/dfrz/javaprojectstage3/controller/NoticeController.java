package com.dfrz.javaprojectstage3.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Notice;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.service.IPetitionService;
import com.dfrz.javaprojectstage3.service.NoticeService;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
     * 编辑公告
     * @param id
     * @return
     */
    @RequestMapping("/tonoticeedit")
    public ModelAndView toNoticeedit(Integer id) {
        ModelAndView mv = new ModelAndView();
        //获取数据
        Notice notice = noticeService.getNoticeById(id);
        mv.setViewName("noticeedit");
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

    /**
     * 添加公告
     * @param noticeJSON
     * @return
     */
    @RequestMapping("/addnotice")
    public Result addnotice(String noticeJSON) {
        logger.info(noticeJSON);
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session=subject.getSession();
        //解析json串
        JSONObject jsonObject =JSON.parseObject(noticeJSON);
        // 获取user参数
        JSONObject addnotice=jsonObject.getJSONObject("dataField");
        String title=addnotice.getString("title");
        String content=addnotice.getString("content");
        String type=addnotice.getString("type");
        Notice notice=new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setType(type);
        noticeService.addnotice(notice);
        Result result=ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

    /**
     * 更新公告
     * @param updatenoticeJSON
     * @return
     */
    @RequestMapping("/updatenotice")
    public Result updatenotice(String updatenoticeJSON) {
        logger.info(updatenoticeJSON);
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session=subject.getSession();
        //解析json串
        JSONObject jsonObject =JSON.parseObject(updatenoticeJSON);
        // 获取notice参数
        JSONObject updatenotice=jsonObject.getJSONObject("dataField");
        Integer id=updatenotice.getInteger("id");
        String title=updatenotice.getString("title");
        String content=updatenotice.getString("content");
        String type=updatenotice.getString("type");
        Integer user_id=updatenotice.getInteger("user_id");
        Notice notice=new Notice();
        notice.setId(id);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setType(type);
        notice.setUserId(user_id);
        noticeService.updatenoticeByid(notice);
        Result result=ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

    /**
     * 删除公告
     * @param deletenoticeJSON
     * @return
     */
    @RequestMapping("/deletenotice")
    public Result deletenotice(String deletenoticeJSON) {
        logger.info(deletenoticeJSON);
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session=subject.getSession();
        //解析json串
        JSONObject jsonObject =JSON.parseObject(deletenoticeJSON);
        // 获取notice参数
        JSONObject updatenotice=jsonObject.getJSONObject("dataField");
        Integer id=updatenotice.getInteger("id");
        String title=updatenotice.getString("title");
        String content=updatenotice.getString("content");
        String type=updatenotice.getString("type");
        Integer user_id=updatenotice.getInteger("user_id");
        Notice notice=new Notice();
        notice.setId(id );
        notice.setTitle(title);
        notice.setContent(content);
        notice.setType(type);
        notice.setUserId(user_id);
        noticeService.deletenoticeById(id);
        Result result=ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

}