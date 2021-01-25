package com.dfrz.javaprojectstage3.bean;

import java.util.Date;

public class Step {
    private Integer id;

    private Integer petitionId;

    private Date auditTime1;

    private Date auditTime2;

    private Date auditTime3;

    private Date createtime;

    private String adviceId;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(String adviceId) {
        this.adviceId = adviceId == null ? null : adviceId.trim();
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", petitionId=" + petitionId +
                ", auditTime1=" + auditTime1 +
                ", auditTime2=" + auditTime2 +
                ", auditTime3=" + auditTime3 +
                ", createtime=" + createtime +
                ", adviceId='" + adviceId + '\'' +
                '}';
    }
}