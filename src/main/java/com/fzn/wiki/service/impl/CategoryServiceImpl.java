package com.fzn.wiki.service.impl;

import com.fzn.wiki.domain.Category;
import com.fzn.wiki.mapper.CategoryMapper;
import com.fzn.wiki.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电子书 服务实现类
 * </p>
 *
 * @author fzn
 * @since 2022-04-16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
