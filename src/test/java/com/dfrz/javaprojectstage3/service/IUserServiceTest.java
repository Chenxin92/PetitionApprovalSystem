package com.dfrz.javaprojectstage3.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author：ChenXin
 * @date 2021/2/20 11:56
 */
@SpringBootTest
class IUserServiceTest {
    @Autowired
    IUserService userService;

    @Test
    void getDepartmentList() {
        List<String> departmentList = userService.getDepartmentList();
        for (String s : departmentList) {
            System.out.println(s);
        }
    }
}