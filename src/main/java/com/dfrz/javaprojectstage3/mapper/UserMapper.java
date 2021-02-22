package com.dfrz.javaprojectstage3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dfrz.javaprojectstage3.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取部门列表
     *
     * @return
     */
    List<String> getDepartmentList();
    public List getDepartmentcount(String department);
}