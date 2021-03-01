package com.cx.javaprojectstage3.userTest;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.javaprojectstage3.bean.Permission;
import com.cx.javaprojectstage3.bean.User;
import com.cx.javaprojectstage3.mapper.PermissionMapper;
import com.cx.javaprojectstage3.mapper.UserMapper;
import com.cx.javaprojectstage3.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    IUserService userService;
    @Autowired
    PermissionMapper permissionMapper;
    @Test
    public void test01(){

    }
    @Test
    public void test02(){
        Page page=new Page();
        page.setCurrent(2);
        page.setSize(5);
        IPage iPage=userService.getUsersByPage(page);
        List<User> list=iPage.getRecords();
        for (User u:list
                ) {
            System.out.println(u);
        }
    }
    @Test
    public void test03(){
        User user=userService.getUserRolePermissionByUname("1");
        System.out.println(user);
        System.out.println(user.getUrole().getRoleName());
        List<Permission> permissions=user.getUrole().getPermissions();
        for (Permission p:permissions
                ) {
            System.out.println(p.getPermissionKey());
        }
    }
    @Test
    public void test04(){
        Integer result=permissionMapper.getRolePermissionCount(1,7);
        System.out.println(result);
    }




    }




