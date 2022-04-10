package com.fzn.wiki.service;

import com.fzn.wiki.domain.Book;
import com.fzn.wiki.domain.BookExample;
import com.fzn.wiki.domain.request.BookRequest;
import com.fzn.wiki.domain.response.BookResponse;
import com.fzn.wiki.mapper.BookMapper;
import com.fzn.wiki.util.CopyUtil;
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
    @Resource
    private BookMapper bookMapper;

    public List<Book> list() {
        return bookMapper.selectByExample(null);
    }

    public List<BookResponse> listByName(BookRequest req) {
        BookExample bookExample = new BookExample();
        if (!ObjectUtils.isEmpty(req.getName())) {
            bookExample.createCriteria().andNameLike(req.getName() + "%");
        }
        List<Book> bookList = bookMapper.selectByExample(bookExample);

        List<BookResponse> responseList = CopyUtil.copyList(bookList, BookResponse.class);
        return responseList;
    }
}
