package com.fzn.wiki.controller;

import com.fzn.wiki.domain.Book;
import com.fzn.wiki.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Book> book() {
        return bookService.list();
    }
}

