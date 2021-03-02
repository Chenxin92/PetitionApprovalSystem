package com.cx.javaprojectstage3.bean;

import lombok.Data;

/**
 * 统计类
 *
 * @author：ChenXin
 * @date 2021/2/22 23:59
 */
@Data
public class Count {
    /**
     * 部门
     */
    private String department;
    /**
     * 总数
     */
    private Integer total;

    public Count() {
    }
}
