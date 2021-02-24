package com.dfrz.javaprojectstage3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.DictionaryData;
import com.dfrz.javaprojectstage3.bean.Notice;
import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.service.DictionaryDataService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.Subject;

/**
 * 作者：zhengyefeng
 * 日期: 2021/2/24 15:28
 * 描述:
 */
@RestController
@RequestMapping("/DictionaryData")
public class DictionaryDataController {
    @Autowired
    DictionaryDataService dictionaryDataService;

    /**
     * 资料库页面数据
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/DictionaryDatalistAjax")
    public Result getList1(Integer page, Integer limit) {
        // List<User> list=userService.getUsers();
        //分页
        Page p = new Page();
        p.setCurrent(page);
        p.setSize(limit);

        IPage iPage = dictionaryDataService.getDictionaryDataByPage(p);
        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
    }


    /**
     * 详细资料页面
     * @return
     */
    @RequestMapping("/todictionaryDatadetails")
    public ModelAndView todictionaryDataDetails(Integer id) {
        ModelAndView mv = new ModelAndView();
        //获取数据
        DictionaryData dictionaryData=dictionaryDataService.getDictionaryDataById(id);
        mv.setViewName("datalist");
        mv.addObject("dictionaryData", dictionaryData);
        return mv;
    }

    /**
     * 编辑资料库
     * @param id
     * @return
     */
    @RequestMapping("/todataedit")
    public ModelAndView todataedit(Integer id) {
        ModelAndView mv = new ModelAndView();
        //获取数据
        DictionaryData dictionaryData = dictionaryDataService.getDictionaryDataById(id);
        mv.setViewName("dataedit");
        mv.addObject("Data", dictionaryData);
        return mv;
    }
    /**
     * 添加资料页面
     * @return
     */
    @RequestMapping("/todataadd")
    public ModelAndView toNoticeadd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("dataadd");
        return mv;
    }
    /**
     * 添加资料
     * @param adddataJSON
     * @return
     */
    @RequestMapping("/adddata")
    public Result addnotice(String adddataJSON) {

        //获取当前登陆用户的信息
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session=subject.getSession();
        //解析json串
        JSONObject jsonObject =JSON.parseObject(adddataJSON);
        // 获取user参数
        JSONObject adddata=jsonObject.getJSONObject("dataField");
        Integer dictionaryId=adddata.getInteger("dictionaryId");
        String dictionaryContent=adddata.getString("dictionaryContent");
        Integer value=adddata.getInteger("value");
        DictionaryData dictionaryData=new DictionaryData();
        dictionaryData.setDictionaryId(dictionaryId);
        dictionaryData.setDictionaryContent(dictionaryContent);
        dictionaryData.setValue(value);
        dictionaryDataService.adddictionaryData(dictionaryData);
        Result result=ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

    /**
     * 更新资料
     * @param updatedataJSON
     * @return
     */
    @RequestMapping("/updatedata")
    public Result updatedata(String updatedataJSON) {
        //获取当前登陆用户的信息
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session=subject.getSession();
        //解析json串
        JSONObject jsonObject =JSON.parseObject(updatedataJSON);
        // 获取notice参数
        JSONObject updatedata=jsonObject.getJSONObject("dataField");
        Integer id=updatedata.getInteger("id");
        Integer dictionaryId=updatedata.getInteger("dictionaryId");
        String dictionaryContent=updatedata.getString("dictionaryContent");
        Integer value=updatedata.getInteger("value");
        DictionaryData dictionaryData=new DictionaryData();
        dictionaryData.setId(id);
        dictionaryData.setDictionaryId(dictionaryId);
        dictionaryData.setDictionaryContent(dictionaryContent);
        dictionaryData.setValue(value);
        dictionaryDataService.updateDictionaryDataByid(dictionaryData);
        Result result=ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

    /**
     * 删除资料
     * @param deletedataJSON
     * @return
     */
    @RequestMapping("/deletdata")
    public Result deletedata(String deletedataJSON) {

        //获取当前登陆用户的信息
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Session session=subject.getSession();
        //解析json串
        JSONObject jsonObject =JSON.parseObject(deletedataJSON);
        // 获取notice参数
        JSONObject deletedata=jsonObject.getJSONObject("dataField");
        Integer id=deletedata.getInteger("id");
        Integer dictionaryId=deletedata.getInteger("dictionaryId");
        String dictionaryContent=deletedata.getString("dictionaryContent");
        Integer value=deletedata.getInteger("value");
        DictionaryData dictionaryData=new DictionaryData();
        dictionaryData.setId(id);
        dictionaryData.setDictionaryId(dictionaryId);
        dictionaryData.setDictionaryContent(dictionaryContent);
        dictionaryData.setValue(value);
        dictionaryDataService.deleteDictionaryDataById(id);
        Result result=ResultUtils.success(1);
        result.setCode(0);
        return result;
    }

}

