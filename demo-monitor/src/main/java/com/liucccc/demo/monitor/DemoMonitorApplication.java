package com.liucccc.demo.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 应用监控
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/15 15:10
 */
@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class DemoMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoMonitorApplication.class, args);
    }
}
