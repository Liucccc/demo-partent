package com.liucccc.demo.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * DemoAdminApplication
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/1 16:50
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.liucccc.demo.admin.dao")
public class DemoAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoAdminApplication.class, args);
    }
}
