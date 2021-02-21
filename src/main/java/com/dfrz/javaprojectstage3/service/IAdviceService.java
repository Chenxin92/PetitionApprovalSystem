package com.dfrz.javaprojectstage3.service;

import com.dfrz.javaprojectstage3.bean.Advice;

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
    Boolean addAdvice(Advice advice,Integer petitionID);
}
