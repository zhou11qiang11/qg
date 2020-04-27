package com.qg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QgGoodsProvider {
    public static void main(String[] args) {
        SpringApplication.run(QgGoodsProvider.class,args);
        System.out.println("QgGoodsProvider------------>启动成功");
    }
}
