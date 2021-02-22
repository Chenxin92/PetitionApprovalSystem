package com.dfrz.javaprojectstage3.bean;

/**
 * 统计类
 *
 * @author：ChenXin
 * @date 2021/2/22 23:59
 */
public class Count {
    private String department;
    private Integer total;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Count() {
    }

    @Override
    public String toString() {
        return "Count{" +
                "department='" + department + '\'' +
                ", total=" + total +
                '}';
    }
}
