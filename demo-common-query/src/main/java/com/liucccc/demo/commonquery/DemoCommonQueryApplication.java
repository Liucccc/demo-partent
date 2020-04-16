package com.liucccc.demo.commonquery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * DemoCommonQueryApplication
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/1 18:03
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.liucccc.demo.commonquery.dao")
@ComponentScan("com.liucccc.demo")
public class DemoCommonQueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCommonQueryApplication.class, args);
    }
}
