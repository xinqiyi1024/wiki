package com.fzn.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzn.wiki.domain.Category;
import com.fzn.wiki.domain.request.CategoryQueryReq;
import com.fzn.wiki.domain.request.CategorySaveReq;
import com.fzn.wiki.domain.response.CategoryQueryResp;
import com.fzn.wiki.domain.response.PageResp;
import com.fzn.wiki.mapper.CategoryMapper;
import com.fzn.wiki.service.CategoryService;
import com.fzn.wiki.util.CopyUtil;
import com.fzn.wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    private CategoryMapper categoryMapper;

    private SnowFlake snowFlake;

    @Autowired
    public void setSnowFlake(SnowFlake snowFlake) {
        this.snowFlake = snowFlake;
    }

    public PageResp<CategoryQueryResp> listByName(CategoryQueryReq req) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        // 前段传过来
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Category> categoryList = categoryMapper.selectList(wrapper);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        // 返回list和总条数
        LOG.info("总条数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<CategoryQueryResp> responseList = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResponse = new PageResp<>();
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setList(responseList);
        return pageResponse;
    }

    public List<CategoryQueryResp> all() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        List<Category> categoryList = categoryMapper.selectList(wrapper);
        // 列表复制
        return CopyUtil.copyList(categoryList, CategoryQueryResp.class);
    }

    /**
     * 保存
     * @param req
     */
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        } else {
            // 根据主键更新
            categoryMapper.updateById(category);
        }

    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(String id) {
        categoryMapper.deleteById(id);
    }
}
