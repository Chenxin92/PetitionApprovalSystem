package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Sora
 */
@Data
@TableName(value = "t_petition_advice")
public class PetitionAdvice {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer petitionId;

    private Integer adviceId;

    public PetitionAdvice() {
    }
}