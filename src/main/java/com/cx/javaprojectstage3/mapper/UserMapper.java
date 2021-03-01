package com.cx.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.javaprojectstage3.bean.Count;
import com.cx.javaprojectstage3.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取部门列表
     *
     * @return
     */
    List<String> getDepartmentList();

    public List getDepartmentcount(String department);

    /**
     * 查询所有部门及对应部门人数
     *
     * @return
     */
    List<Count> getCountList();
}