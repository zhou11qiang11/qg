package com.qg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PreApplication {
    public static void main(String[] args) {
        SpringApplication.run(PreApplication.class,args);
        System.out.println("PreApplication------------->启动成功");
    }
}
