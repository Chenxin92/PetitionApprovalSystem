package com.cx.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Count;
import com.cx.javaprojectstage3.bean.User;

import java.util.List;

public interface IUserService {
    boolean login(User user);

    List<User> getUsers();

    void save(User user);

    int updateuserById(User user);

    User getUserById(Integer id);

    IPage<User> getUsersByPage(Page<?> page);

    User getUserRolePermissionByUname(String username);

    int adduser(User user);

    User getUserByUname(String uname);

    int deleteuser(Integer id);

    /**
     * 获取所有部门列表
     *
     * @return
     */
    List<String> getDepartmentList();

    /**
     * 根据部门获取二级审批人列表
     *
     * @param department
     * @return
     */
    List<User> getTwoPrincipalListByDepartment(String department);

    /**
     * 获取部门统计
     *
     * @return
     */
    List<Count> getCountList();
}
