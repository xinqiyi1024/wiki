package com.fzn.wiki.service;

import com.fzn.wiki.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fzn.wiki.domain.request.CategoryQueryReq;
import com.fzn.wiki.domain.request.CategorySaveReq;
import com.fzn.wiki.domain.response.CategoryQueryResp;
import com.fzn.wiki.domain.response.PageResp;

/**
 * <p>
 * 电子书 服务类
 * </p>
 *
 * @author fzn
 * @since 2022-04-16
 */
public interface CategoryService extends IService<Category> {
    PageResp<CategoryQueryResp> listByName(CategoryQueryReq req);

    void save(CategorySaveReq req);

    void delete(String id);
}
