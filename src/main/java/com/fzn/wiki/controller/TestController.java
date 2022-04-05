package com.fzn.wiki.controller;

import com.fzn.wiki.domain.Test;
import com.fzn.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author FZN
 * @date 2022/04/04 16:56
 * @description 测试
 */
@RestController
public class TestController {
    @Value("${test.hello:def}")
    private String testHello;

    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test/list")
    public List<Test> test() {
        return testService.list();
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello post " + name;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello==>" + testHello;
    }
}


