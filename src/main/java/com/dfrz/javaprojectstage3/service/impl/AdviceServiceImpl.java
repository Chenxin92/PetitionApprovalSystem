package com.dfrz.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dfrz.javaprojectstage3.bean.Advice;
import com.dfrz.javaprojectstage3.bean.AttachFile;
import com.dfrz.javaprojectstage3.bean.Petition;
import com.dfrz.javaprojectstage3.bean.PetitionAdvice;
import com.dfrz.javaprojectstage3.mapper.*;
import com.dfrz.javaprojectstage3.service.IAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者：wenqi
 * 日期: 2021/2/20 23:31
 * 描述:
 */
@Service
public class AdviceServiceImpl implements IAdviceService {

    @Autowired
    PetitionMapper petitionMapper;
    @Autowired
    AttachFileMapper attachFileMapper;
    @Autowired
    StepMapper stepMapper;
    @Autowired
    AdviceMapper adviceMapper;
    @Autowired
    PetitionAdviceMapper petitionAdviceMapper;

    @Override
    public Boolean addAdvice(Advice advice , Integer petitionID) {
        // 添加petition失败返回
        if (adviceMapper.insert(advice) <= 0) {
            return false;
        }

        PetitionAdvice petitionAdvice=new PetitionAdvice();

        petitionAdvice.setAdviceId(advice.getId());
        petitionAdvice.setPetitionId(petitionID);
        if (petitionAdviceMapper.insert(petitionAdvice) <= 0) {
            return false;
        }
        // 添加附件
        List<AttachFile> adviceAttachList = advice.getAttachFileList();
        // 无附件直接返回
        if (adviceAttachList == null) {
            return true;
        }
        for (AttachFile attachFile : adviceAttachList) {
            // 设置对象ID
            attachFile.setObjectId(advice.getId());
            if (attachFileMapper.insert(attachFile) <= 0) {
                adviceMapper.deleteById(advice.getId());
                return false;
            }
        }

        return true;
    }

    @Override
    public Advice getAdviceById(Integer id) {
        return adviceMapper.selectById(id);
    }

    @Override
    public Advice getAdviceByCreateUser(Integer userId) {
        QueryWrapper<Advice> adviceQueryWrapper = new QueryWrapper<>();
        adviceQueryWrapper.eq("create_user", userId);
        return adviceMapper.selectOne(adviceQueryWrapper);
    }

    @Override
    public Petition getPetitionByAdviceId(Integer adviceId) {
        return adviceMapper.getPetitionByAdviceId(adviceId);
    }
}
