package com.liucccc.demo.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 应用监控
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/15 15:10
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class DemoMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoMonitorApplication.class, args);
    }
}
