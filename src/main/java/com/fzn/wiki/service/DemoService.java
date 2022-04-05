package com.fzn.wiki.service;

import com.fzn.wiki.domain.Demo;
import com.fzn.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author FZN
 * @date 2022/04/05 14:29
 * @description
 */
@Service
public class DemoService {
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectByExample(null);
    }
}
