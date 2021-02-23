package com.dfrz.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.Count;
import com.dfrz.javaprojectstage3.bean.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    public boolean login(User user);

    public List<User> getUsers();

    public void save(User user);

    public int updateuserById(User user);

    public User getUserById(Integer id);

    public IPage<User> getUsersByPage(Page<?> page);

    public User getUserRolePermissionByUname(String username);

    public int adduser(User user);

    public User getUserByUname(String uname);

    public User findById(Integer id);

    public int deleteuser(Integer id);

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
     * 获取部门名称
     *
     * @return
     */
    public List<String> getDepartmentName();

    /**
     * 获取部门人数统计
     *
     * @return
     */
    public List<Integer> getgetDepartmentCount();

    /**
     * 获取部门统计
     *
     * @return
     */
    List<Count> getCountList();
}
