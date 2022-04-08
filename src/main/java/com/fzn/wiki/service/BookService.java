package com.fzn.wiki.service;

import com.fzn.wiki.domain.Book;
import com.fzn.wiki.domain.BookExample;
import com.fzn.wiki.domain.request.BookRequest;
import com.fzn.wiki.domain.response.BookResponse;
import com.fzn.wiki.mapper.BookMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    public List<BookResponse> listByName(BookRequest req) {
        BookExample bookExample = new BookExample();
        bookExample.createCriteria().andNameLike(req.getName() + "%");
        List<Book> bookList = bookMapper.selectByExample(bookExample);

        List<BookResponse> responseList = new ArrayList<>();

        for (Book book : bookList) {
            BookResponse bookResponse = new BookResponse();
            BeanUtils.copyProperties(book, bookResponse);
            responseList.add(bookResponse);
        }
        return responseList;
    }
}
