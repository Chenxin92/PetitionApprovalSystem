package com.cx.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.javaprojectstage3.bean.Advice;
import com.cx.javaprojectstage3.bean.Petition;
import org.springframework.stereotype.Repository;

@Repository
public interface AdviceMapper extends BaseMapper<Advice> {
    /**
     * 根据意见ID查关联表获取信访件
     *
     * @param adviceId
     * @return
     */
    Petition getPetitionByAdviceId(Integer adviceId);
}