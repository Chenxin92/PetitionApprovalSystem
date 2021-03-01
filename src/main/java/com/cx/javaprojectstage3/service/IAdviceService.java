package com.cx.javaprojectstage3.service;

import com.cx.javaprojectstage3.bean.Advice;
import com.cx.javaprojectstage3.bean.Petition;

/**
 * 作者：wenqi
 * 日期: 2021/2/20 23:30
 * 描述:
 */
public interface IAdviceService {
    /**
     * 添加审批意见
     *
     * @param advice
     * @return
     */
    Boolean addAdvice(Advice advice, Integer petitionID);

    /**
     * 根据ID获取意见
     *
     * @param id
     * @return
     */
    Advice getAdviceById(Integer id);

    Advice getAdviceByCreateUser(Integer userId);

    /**
     * 根据意见ID获取信访件
     *
     * @param adviceId
     * @return
     */
    Petition getPetitionByAdviceId(Integer adviceId);
}
