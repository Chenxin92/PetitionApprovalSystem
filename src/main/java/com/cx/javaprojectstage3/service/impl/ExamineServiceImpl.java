package com.cx.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Petition;
import com.cx.javaprojectstage3.bean.User;
import com.cx.javaprojectstage3.mapper.AttachFileMapper;
import com.cx.javaprojectstage3.mapper.PetitionMapper;
import com.cx.javaprojectstage3.mapper.StepMapper;
import com.cx.javaprojectstage3.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public IPage<Petition> getPetitionPagebyCurrentuserAndTime(Page page,Integer examineuser,Integer userid, Integer petitionstate,String acceptTime) {
        IPage<Petition> ipage=stepMapper.getPetitionPagebyCurrentuserAndTime(page,examineuser,userid,petitionstate,acceptTime);
        return ipage;

    }

    @Override
    public IPage<Petition> getPetitionPagebyCurrentuser(Page page, Integer examineuser, Integer userid, Integer petitionstate) {
        IPage<Petition> ipage=stepMapper.getPetitionPagebyCurrentuser(page,examineuser,userid,petitionstate);
        return ipage;
    }

    @Override
    public List<User> getUserListByDepartment(String department,Integer role) {
        List<User> users=stepMapper.getUserListByDepartment(department,role);
        return users;
    }
}
