package com.qg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QgGoodsConsumer {
    public static void main(String[] args) {
        SpringApplication.run(QgGoodsConsumer.class,args);
        System.out.println("QgGoodsConsumer------------->启动成功");
    }
}
