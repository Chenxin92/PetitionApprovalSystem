package com.dfrz.javaprojectstage3.service.impl;

import com.dfrz.javaprojectstage3.bean.Step;
import com.dfrz.javaprojectstage3.mapper.StepMapper;
import com.dfrz.javaprojectstage3.service.IStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：ChenXin
 * @date 2021/2/20 21:55
 */
@Service
public class StepServiceImpl implements IStepService {
    @Autowired
    StepMapper stepMapper;

    @Override
    public Boolean addStep(Step step) {
        return stepMapper.insert(step) > 0;
    }
}
