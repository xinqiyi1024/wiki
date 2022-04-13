package com.fzn.wiki.service;

import com.fzn.wiki.domain.Book;
import com.fzn.wiki.domain.BookExample;
import com.fzn.wiki.domain.request.BookQueryReq;
import com.fzn.wiki.domain.request.BookSaveReq;
import com.fzn.wiki.domain.response.BookQueryResp;
import com.fzn.wiki.domain.response.PageResp;
import com.fzn.wiki.mapper.BookMapper;
import com.fzn.wiki.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author FZN
 * @date 2022/04/05 14:29
 * @description
 */
@Service
public class BookService {
    private static final Logger LOG = LoggerFactory.getLogger(BookService.class);
    @Resource
    private BookMapper bookMapper;

    public PageResp<BookQueryResp> listByName(BookQueryReq req) {
        BookExample bookExample = new BookExample();
        if (!ObjectUtils.isEmpty(req.getName())) {
            bookExample.createCriteria().andNameLike(req.getName() + "%");
        }
        // 前段传过来
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Book> bookList = bookMapper.selectByExample(bookExample);

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

    public void save(BookSaveReq req) {
        Book book = CopyUtil.copy(req, Book.class);

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            bookMapper.insert(book);
        } else {
            // 根据主键更新
            bookMapper.updateByPrimaryKey(book);
        }

    }
}
