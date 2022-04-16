package com.fzn.wiki.mapper;

import com.fzn.wiki.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 电子书 Mapper 接口
 * </p>
 *
 * @author fzn
 * @since 2022-04-16
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
