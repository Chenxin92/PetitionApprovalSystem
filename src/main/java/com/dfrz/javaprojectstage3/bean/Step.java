package com.dfrz.javaprojectstage3.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value = "t_step")
public class Step {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer petitionId;

    private Date auditTime1;

    private Date auditTime2;

    private Date auditTime3;

    private Integer examineUser1;

    private Integer examineUser2;

    private Integer examineUser3;

    private Date createtime;

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

    public Date getAuditTime1() {
        return auditTime1;
    }

    public void setAuditTime1(Date auditTime1) {
        this.auditTime1 = auditTime1;
    }

    public Date getAuditTime2() {
        return auditTime2;
    }

    public void setAuditTime2(Date auditTime2) {
        this.auditTime2 = auditTime2;
    }

    public Date getAuditTime3() {
        return auditTime3;
    }

    public void setAuditTime3(Date auditTime3) {
        this.auditTime3 = auditTime3;
    }

    public Integer getExamineUser1() {
        return examineUser1;
    }

    public void setExamineUser1(Integer examineUser1) {
        this.examineUser1 = examineUser1;
    }

    public Integer getExamineUser2() {
        return examineUser2;
    }

    public void setExamineUser2(Integer examineUser2) {
        this.examineUser2 = examineUser2;
    }

    public Integer getExamineUser3() {
        return examineUser3;
    }

    public void setExamineUser3(Integer examineUser3) {
        this.examineUser3 = examineUser3;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Step() {
    }

    public Step(Integer id, Integer petitionId, Date auditTime1, Date auditTime2, Date auditTime3, Integer examineUser1, Integer examineUser2, Integer examineUser3, Date createtime) {
        this.id = id;
        this.petitionId = petitionId;
        this.auditTime1 = auditTime1;
        this.auditTime2 = auditTime2;
        this.auditTime3 = auditTime3;
        this.examineUser1 = examineUser1;
        this.examineUser2 = examineUser2;
        this.examineUser3 = examineUser3;
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", petitionId=" + petitionId +
                ", auditTime1=" + auditTime1 +
                ", auditTime2=" + auditTime2 +
                ", auditTime3=" + auditTime3 +
                ", examineUser1=" + examineUser1 +
                ", examineUser2=" + examineUser2 +
                ", examineUser3=" + examineUser3 +
                ", createtime=" + createtime +
                '}';
    }
}