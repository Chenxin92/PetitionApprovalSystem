package com.cx.javaprojectstage3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Notice;
import com.cx.javaprojectstage3.bean.User;
import com.cx.javaprojectstage3.service.IUserService;
import com.cx.javaprojectstage3.service.NoticeService;
import com.cx.javaprojectstage3.utils.Result;
import com.cx.javaprojectstage3.utils.ResultUtils;
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
 * 日期: 2021/1/26 14:36
 * 描述:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    NoticeService noticeService;
    @Autowired
    IUserService userService;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public ModelAndView toLogin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    /**
     * 用户列表页面
     *
     * @return
     */
    @RequestMapping("/touseradd")
    public ModelAndView toUseradd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("useradd");
        return mv;
    }

    /**
     * 用户列表页面
     *
     * @return
     */
    @RequestMapping("/toUserList")
    public ModelAndView toUserList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userlist");
        return mv;
    }

    /**
     * 用户列表页面数据
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/listAjax")
    public Result getList(Integer page, Integer limit) {
        // List<User> list=userService.getUsers();
        //分页
        Page p = new Page();
        p.setCurrent(page);
        p.setSize(limit);
        IPage iPage = userService.getUsersByPage(p);
        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
    }

    /**
     * 信访单列表页面数据
     *
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
     * 查看用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/todetails")
    public ModelAndView toDetails(Integer id) {
        ModelAndView mv = new ModelAndView();
        //获取数据
        User user = userService.getUserById(id);
        mv.setViewName("userdetails");
        mv.addObject("user", user);
        return mv;
    }


    /**
     * 查看信访件
     *
     * @param id
     * @return
     */
    @RequestMapping("/toletterdetails")
    public ModelAndView toLetterdetails(Integer id) {
        ModelAndView mv = new ModelAndView();
        //获取数据
        Notice notice = noticeService.getNoticeById(id);
        mv.setViewName("letterdetails");
        mv.addObject("notice", notice);
        return mv;
    }

    /**
     * 编辑用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/todeit")
    public ModelAndView toDeit(Integer id) {
        ModelAndView mv = new ModelAndView();
        //获取数据
        User user = userService.getUserById(id);
        mv.setViewName("useredit");
        mv.addObject("user", user);
        return mv;
    }

    /**
     * 登录方法
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public Map<String, String> login(User user) {
        logger.info("execute login method. Login uname=" + user.getUsername() + ",upass=" + user.getPassword());
        Map<String, String> map = new HashMap<>();
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), user.getRealname());
        // 3. 执行shiro的login方法--会自动调用Realm中的认证方法doGetAuthenticationInfo
        try {
            subject.login(token);
            //登陆成功后，将用户的信息保存至session中
            User currentUser = (User) subject.getPrincipal();
            session.setAttribute("currentUser", user);
            map.put("flag", "1");
        } catch (UnknownAccountException e) {
            logger.info("没有此账号");
            map.put("flag", "-1");
        } catch (IncorrectCredentialsException e) {
            logger.info("密码不正确");
            map.put("flag", "-2");
        } catch (AuthenticationException e) {
            logger.info("认证失败");
            map.put("flag", "-3");
        }
        return map;
    }

    @RequestMapping("/adduser")
    public Result adduser(String userJSON) {
        logger.info(userJSON);
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session = subject.getSession();
        //解析json串
        JSONObject jsonObject = JSON.parseObject(userJSON);
        // 获取user参数
        JSONObject adduser = jsonObject.getJSONObject("dataField");
        String username = adduser.getString("username");
        String password = adduser.getString("password");
        Integer role = adduser.getInteger("role");
        String department = adduser.getString("department");
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        user1.setRole(role);
        user1.setDepartment(department);
        userService.adduser(user1);
        Result result = ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

    /**
     * 更新用户
     *
     * @param updateuserJSON
     * @return
     */
    @RequestMapping("/updateuser")
    public Result updateuser(String updateuserJSON) {
        logger.info(updateuserJSON);
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session = subject.getSession();
        //解析json串
        JSONObject jsonObject = JSON.parseObject(updateuserJSON);
        // 获取user参数
        JSONObject updateuser = jsonObject.getJSONObject("dataField");
        Integer id = updateuser.getInteger("id");
        String username = updateuser.getString("username");
        String realname = updateuser.getString("realname");
        String sex = updateuser.getString("sex");
        String phone = updateuser.getString("phone");
        String email = updateuser.getString("email");
        String department = updateuser.getString("department");
        Integer role = updateuser.getInteger("role");
        String status = updateuser.getString("status");
        String address = updateuser.getString("address");
        User user1 = new User();
        user1.setId(id);
        user1.setUsername(username);
        user1.setRealname(realname);
        user1.setSex(sex);
        user1.setPhone(phone);
        user1.setEmail(email);
        user1.setDepartment(department);
        user1.setRole(role);
        user1.setStatus(status);
        user1.setAddress(address);
        userService.updateuserById(user1);
        Result result = ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

    /**
     * 删除用户
     *
     * @param deleteuserJSON
     * @return
     */
    @RequestMapping("/deleteuser")
    public Result deleteuser(String deleteuserJSON) {
        logger.info(deleteuserJSON);
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session = subject.getSession();
        //解析json串
        JSONObject jsonObject = JSON.parseObject(deleteuserJSON);
        // 获取user参数
        JSONObject deleteuser = jsonObject.getJSONObject("dataField");
        Integer id = deleteuser.getInteger("id");
        String username = deleteuser.getString("username");
        User user1 = new User();
        user1.setId(id);
        user1.setUsername(username);
        userService.deleteuser(id);
        Result result = ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

    /**
     * 是否为人事部经理或者领导
     *
     * @return
     */
    @RequestMapping("/userEdit")
    public boolean userEdit() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user.getRole() == 3 || user.getRole() == 4) {
            if (user.getDepartment().equals("人事部门")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout() {
        SecurityUtils.getSubject().logout();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
