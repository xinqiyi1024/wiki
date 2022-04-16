package com.fzn.wiki.controller;

import com.fzn.wiki.domain.request.BookQueryReq;
import com.fzn.wiki.domain.request.BookSaveReq;
import com.fzn.wiki.domain.response.BookQueryResp;
import com.fzn.wiki.domain.response.CommonResp;
import com.fzn.wiki.domain.response.PageResp;
import com.fzn.wiki.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 电子书 前端控制器
 * </p>
 *
 * @author fzn
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    @Autowired
    @Qualifier("bookServiceImpl")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public CommonResp listByName(BookQueryReq req) {
        CommonResp<PageResp<BookQueryResp>> resp = new CommonResp<>();
        PageResp<BookQueryResp> list = bookService.listByName(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody BookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        bookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable String id) {
        CommonResp resp = new CommonResp<>();
        bookService.delete(id);
        return resp;
    }
}