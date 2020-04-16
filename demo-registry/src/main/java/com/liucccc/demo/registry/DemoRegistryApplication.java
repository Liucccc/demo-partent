package com.liucccc.demo.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * DemoRegistryApplication
 * <br>
 *
 * @author liuchao
 * @created date 2020/4/3 11:31
 */
@EnableEurekaServer
@SpringBootApplication
public class DemoRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoRegistryApplication.class, args);
    }
}
