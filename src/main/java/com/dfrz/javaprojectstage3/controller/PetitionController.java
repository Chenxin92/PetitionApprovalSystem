package com.dfrz.javaprojectstage3.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.service.IPetitionService;
import com.dfrz.javaprojectstage3.utils.Result;
import com.dfrz.javaprojectstage3.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 信访件
 * @author Chenxin
 * @date 2021/02/17 1:09
 */
@RestController
@RequestMapping("/petition")
public class PetitionController {
    @Autowired
    IPetitionService petitionService;

    private static Logger logger= LoggerFactory.getLogger(PetitionController.class);

    /**
     * 转至信访件列表页面
     * @return
     */
    @RequestMapping("/toPetitionList")
    public ModelAndView toPetitionList(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("petition_list");
        return mv;
    }

    /**
     * 显示信访件列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getPetitionList")
    public Result getPetitionList(Integer page, Integer limit) {
        // 分页
        Page<Petition> petitionPage = new Page<>();
        petitionPage.setCurrent(page);
        petitionPage.setSize(limit);

        IPage<Petition> iPage = petitionService.getPetitionPage(petitionPage);
        Result result = ResultUtils.success(iPage.getRecords());
        result.setCode(0);
        result.setCount((int) iPage.getTotal());
        return result;
    }



    /**
     * 转至添加信访件页面
     * @return
     */
    @RequestMapping("/toPetitionAdd")
    public ModelAndView toPetitionAdd(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("petition_add");
        return mv;
    }
}
