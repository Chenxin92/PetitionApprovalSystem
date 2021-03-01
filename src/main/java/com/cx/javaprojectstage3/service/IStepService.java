package com.cx.javaprojectstage3.service;

import com.cx.javaprojectstage3.bean.Step;

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

    /**
     * 根据信访件ID获取Step
     *
     * @param PetitionId
     * @return
     */
    Step getStepByPetitionId(Integer PetitionId);

    /**
     * 根据信访件ID更新step
     *
     * @param step
     * @return
     */
    Boolean updatestepById(Step step);
}
