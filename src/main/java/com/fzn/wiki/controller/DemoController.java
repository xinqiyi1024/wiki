package com.fzn.wiki.controller;

import com.fzn.wiki.domain.Demo;
import com.fzn.wiki.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author FZN
 * @date 2022/04/04 16:56
 * @description 测试
 */
@RequestMapping("/demo")
@RestController
public class DemoController {

    private DemoService demoService;

    @Autowired
    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/list1")
    public List<Demo> demo() {
        return demoService.list();
    }
}


