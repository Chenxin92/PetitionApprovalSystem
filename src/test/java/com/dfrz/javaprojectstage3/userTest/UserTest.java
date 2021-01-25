package com.dfrz.javaprojectstage3.userTest;


import com.dfrz.javaprojectstage3.bean.User;
import com.dfrz.javaprojectstage3.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void test01(){
        User user=userMapper.selectById(1);
        System.out.println(user);
    }


    }


