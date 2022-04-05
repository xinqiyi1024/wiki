package com.fzn.wiki.controller;

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
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello post " + name;
    }
}
