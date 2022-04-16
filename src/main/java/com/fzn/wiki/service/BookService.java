package com.fzn.wiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzn.wiki.domain.Book;
import com.fzn.wiki.domain.request.BookQueryReq;
import com.fzn.wiki.domain.request.BookSaveReq;
import com.fzn.wiki.domain.response.BookQueryResp;
import com.fzn.wiki.domain.response.PageResp;

/**
 * <p>
 * 电子书 服务类
 * </p>
 *
 * @author fzn
 * @since 2022-04-16
 */
public interface BookService extends IService<Book> {

    PageResp<BookQueryResp> listByName(BookQueryReq req);

    void save(BookSaveReq req);

    void delete(Long id);

}
