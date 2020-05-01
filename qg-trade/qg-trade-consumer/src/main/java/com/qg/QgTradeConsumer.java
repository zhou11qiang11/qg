package com.qg;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableDubbo
@ServletComponentScan
public class QgTradeConsumer {
    public static void main(String[] args) {
        SpringApplication.run(QgTradeConsumer.class,args);
    }
}
