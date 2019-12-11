package com.liucccc.demo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Liucccc
 * @date 2019/12/11 20:45
 */
@SpringBootApplication
@ComponentScan("com.liucccc.demo")
@MapperScan("com.liucccc.demo")
public class DemoWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class, args);
    }
}
