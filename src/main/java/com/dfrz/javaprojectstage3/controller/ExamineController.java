package com.dfrz.javaprojectstage3.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Dictionary;
import com.dfrz.javaprojectstage3.bean.DictionaryData;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.service.ExamineService;
import com.dfrz.javaprojectstage3.service.IDictionaryService;
import com.dfrz.javaprojectstage3.service.IPetitionService;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2021/2/18 15:59
 * 描述: 领导审批
 */

@RestController
@RequestMapping("/examine1")
public class ExamineController {

    @Autowired
    IPetitionService petitionService;
    @Autowired
    IDictionaryService dictionaryService;
    @Autowired
    ExamineService examineService;

    private static Logger logger = LoggerFactory.getLogger(PetitionController.class);

    /**
     * 转至信访件列表页面
     *
     * @return
     */
    @RequestMapping("/toExamineList")
    public ModelAndView toExamineList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("examine_list");
        return mv;
    }

    /**
     * 显示信访件列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getExamineList")
    public Result getExamineList(Integer page, Integer limit) {
        // 分页
        Page<Petition> petitionPage = new Page<>();
        petitionPage.setCurrent(page);
        petitionPage.setSize(limit);
        ModelAndView mv=new ModelAndView();
        //获取当前登陆用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        IPage<Petition> iPage ;

        if (user.getRole()==3){
             iPage = examineService.getPetitionPagebyCurrentuser(petitionPage,2,user.getId());
        }else  {
             iPage = examineService.getPetitionPagebyCurrentuser(petitionPage,3,user.getId());
        }

        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
    }

    /**
     * 转至查看信访件页面
     *
     * @return
     */
    @RequestMapping("/toView")
    public ModelAndView toView(Integer petitionId) {
        // 获取信访件
        Petition petition = petitionService.getPetitionById(petitionId);
        // 获取信源值
        Dictionary petitionDictionary = dictionaryService.getDictionaryByTypeKey("petition");
        List<DictionaryData> petitionDictionaryDataList = petitionDictionary.getDictionaryDataList();
        String petitionType = "";
        for (DictionaryData dictionaryData : petitionDictionaryDataList) {
            if (dictionaryData.getValue().equals(petition.getPetitionType())) {
                petitionType = dictionaryData.getDictionaryContent();
                break;
            }
        }

        // 获取内容值
        Dictionary contentDictionary = dictionaryService.getDictionaryByTypeKey("content");
        List<DictionaryData> contentDictionaryDataList = contentDictionary.getDictionaryDataList();
        String contentType = "";
        for (DictionaryData dictionaryData : contentDictionaryDataList) {
            if (dictionaryData.getValue().equals(petition.getPetitionType())) {
                contentType = dictionaryData.getDictionaryContent();
                break;
            }
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("petition_view");
        mv.addObject(petition);
        mv.addObject("petitionValue", petitionType);
        mv.addObject("contentValue", contentType);
        return mv;
    }


}
