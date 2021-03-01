package com.cx.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "t_petition_advice")
public class PetitionAdvice {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer petitionId;

    private Integer adviceId;

    public PetitionAdvice() {
    }

    public PetitionAdvice(Integer id, Integer petitionId, Integer adviceId) {
        this.id = id;
        this.petitionId = petitionId;
        this.adviceId = adviceId;
    }

    @Override
    public String toString() {
        return "PetitionAdvice{" +
                "id=" + id +
                ", petitionId=" + petitionId +
                ", adviceId=" + adviceId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetitionId() {
        return petitionId;
    }

    public void setPetitionId(Integer petitionId) {
        this.petitionId = petitionId;
    }

    public Integer getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }
}