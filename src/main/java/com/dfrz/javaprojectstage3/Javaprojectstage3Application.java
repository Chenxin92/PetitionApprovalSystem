package com.dfrz.javaprojectstage3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dfrz.javaprojectstage3.mapper")
public class Javaprojectstage3Application {

    public static void main(String[] args) {
        SpringApplication.run(Javaprojectstage3Application.class, args);
    }

}
