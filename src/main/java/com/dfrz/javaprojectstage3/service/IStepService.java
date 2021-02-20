package com.dfrz.javaprojectstage3.service;

import com.dfrz.javaprojectstage3.bean.Step;

/**
 * @author：ChenXin
 * @date 2021/2/20 21:54
 */
public interface IStepService {
    /**
     * 添加Step
     *
     * @param step
     * @return
     */
    Boolean addStep(Step step);
}
