package com.fzn.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FZN
 * @date 2022/04/04 16:56
 * @description 测试
 */
@RestController
public class TestController {
    @Value("${test.hello:def}")
    private String testHello;

    @GetMapping("/hello")
    public String hello() {
        return "hello==>" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello post " + name;
    }
}
