package com.fzn.wiki.service;

import com.fzn.wiki.domain.Book;
import com.fzn.wiki.mapper.BookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author FZN
 * @date 2022/04/05 14:29
 * @description
 */
@Service
public class BookService {
    @Resource
    private BookMapper bookMapper;

    public List<Book> list() {
        return bookMapper.selectByExample(null);
    }
}
