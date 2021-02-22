package com.dfrz.javaprojectstage3.service.impl;

import com.dfrz.javaprojectstage3.bean.Count;
import com.dfrz.javaprojectstage3.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author：ChenXin
 * @date 2021/2/23 0:08
 */
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    IUserService userService;
    @Test
    void getCountList() {
        List<Count> countList = userService.getCountList();
        for (Count count : countList) {
            System.out.println(count);
        }
    }
}