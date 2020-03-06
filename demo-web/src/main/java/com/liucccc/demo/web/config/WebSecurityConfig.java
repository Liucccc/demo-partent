package com.liucccc.demo.web.config;

import com.liucccc.demo.biz.sys.SysUserService;
import com.liucccc.demo.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * WebSecurityConfig
 *
 * @author liuchao
 * @date 2020/2/6 16:27
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends SecurityConfig {

    @Autowired
    private SysUserService sysUserService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> sysUserService.loadUserByUsername(username);
    }
}
