package com.fzn.wiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzn.wiki.domain.Book;
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
public interface BookMapper extends BaseMapper<Book> {

}
