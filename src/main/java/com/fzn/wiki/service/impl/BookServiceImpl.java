package com.fzn.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzn.wiki.domain.Book;
import com.fzn.wiki.domain.request.BookQueryReq;
import com.fzn.wiki.domain.request.BookSaveReq;
import com.fzn.wiki.domain.response.BookQueryResp;
import com.fzn.wiki.domain.response.PageResp;
import com.fzn.wiki.mapper.BookMapper;
import com.fzn.wiki.service.BookService;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    @Resource
    private BookMapper bookMapper;

    private SnowFlake snowFlake;

    @Autowired
    public void setSnowFlake(SnowFlake snowFlake) {
        this.snowFlake = snowFlake;
    }

    public PageResp<BookQueryResp> listByName(BookQueryReq req) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if (!ObjectUtils.isEmpty(req.getName())) {
            wrapper.likeRight("name", req.getName());
        }
        // 前段传过来
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Book> bookList = bookMapper.selectList(wrapper);

        PageInfo<Book> pageInfo = new PageInfo<>(bookList);
        // 返回list和总条数
        LOG.info("总条数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<BookQueryResp> responseList = CopyUtil.copyList(bookList, BookQueryResp.class);

        PageResp<BookQueryResp> pageResponse = new PageResp<>();
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setList(responseList);
        return pageResponse;
    }

    /**
     * 保存
     * @param req
     */
    public void save(BookSaveReq req) {
        Book book = CopyUtil.copy(req, Book.class);

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            book.setId(snowFlake.nextId());
            bookMapper.insert(book);
        } else {
            // 根据主键更新
            bookMapper.updateById(book);
        }

    }

    /**
     * 根据id删除
     * @param id
     */
    public void delete(Long id) {
        bookMapper.deleteById(id);
    }
}
