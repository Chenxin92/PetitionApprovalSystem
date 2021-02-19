package com.dfrz.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.bean.Step;
import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.mapper.AttachFileMapper;
import com.dfrz.javaprojectstage3.mapper.PetitionMapper;
import com.dfrz.javaprojectstage3.mapper.StepMapper;
import com.dfrz.javaprojectstage3.service.ExamineService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2021/2/19 13:06
 * 描述:
 */
@Service
public class ExamineServiceImpl implements ExamineService {
    @Autowired
    PetitionMapper petitionMapper;
    @Autowired
    AttachFileMapper attachFileMapper;
    @Autowired
    StepMapper stepMapper;


    @Override
    public IPage<Petition> getPetitionPagebyCurrentuser(Page page,Integer examineuser,Integer userid) {
        IPage<Petition> ipage=stepMapper.getPetitionPagebyCurrentuser(page,examineuser,userid);
        return ipage;

    }
}
