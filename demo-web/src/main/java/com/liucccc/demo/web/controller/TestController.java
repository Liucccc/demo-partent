package com.liucccc.demo.web.controller;

import com.liucccc.demo.biz.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liucccc
 * @date 2019/12/11 20:47
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello " + demoService.Test();
    }
}
