package com.dfrz.javaprojectstage3.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfrz.javaprojectstage3.bean.User;


import java.util.List;

public interface IUserService {
    public boolean login(User user);
    public List<User> getUsers();
    public void save(User user);
    public int updateuserById(User user);
    public User getUserById(Integer id);
    public IPage<User> getUsersByPage(Page<?> page);
    public User getUserRolePermissionByUname(String username);
    public int adduser(User user) ;
    public User getUserByUname(String uname);
    public User findById(Integer id);
    public int deleteuser(Integer id);
}
