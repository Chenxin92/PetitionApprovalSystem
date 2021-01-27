package com.dfrz.javaprojectstage3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dfrz.javaprojectstage3.bean.Permission;
import com.dfrz.javaprojectstage3.bean.Role;
import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.mapper.PermissionMapper;
import com.dfrz.javaprojectstage3.mapper.RoleMapper;
import com.dfrz.javaprojectstage3.mapper.UserMapper;
import com.dfrz.javaprojectstage3.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public boolean login(User user) {
        //条件构造器
        System.out.println("进入");
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        Map<String,String> parm=new HashMap<>();
        parm.put("username",user.getUsername());
        parm.put("password",user.getPassword());
        queryWrapper.allEq(parm);
        List<User> list =userMapper.selectList(queryWrapper);
        if(list!=null && list.size()>0){
            return true;
        }

        return false;
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }



    @Override
    public IPage<User> getUsersByPage(Page page) {
        return userMapper.selectPage(page,null);
    }

    @Override
    public User getUserRolePermissionByUname(String username) {
        //1. 根据用户名找用户
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user=userMapper.selectOne(queryWrapper);
        //2. 找角色
        Role role=roleMapper.selectById(user.getRole());
        user.setUrole(role);
        //3.找权限
        List<Permission> permissions=permissionMapper.getPermissionsByRoleId(role.getId());
        role.setPermissions(permissions);

        return user;
    }

    @Override
    public User getUserByUname(String username) {
        //1. 根据用户名找用户
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user=userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }
}