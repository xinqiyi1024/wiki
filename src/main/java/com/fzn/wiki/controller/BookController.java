package com.fzn.wiki.controller;

import com.fzn.wiki.domain.request.BookQueryReq;
import com.fzn.wiki.domain.request.BookSaveReq;
import com.fzn.wiki.domain.response.BookQueryResp;
import com.fzn.wiki.domain.response.CommonResp;
import com.fzn.wiki.domain.response.PageResp;
import com.fzn.wiki.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author FZN
 * @date 2022/04/04 16:56
 * @description 测试
 */
@RequestMapping("/book")
@RestController
public class BookController {
    private BookService bookService;

    @Autowired
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
}


