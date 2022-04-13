package com.fzn.wiki.domain.response;

import java.util.List;

/**
 * @author FZN
 * @date 2022/04/11 16:22
 * @description
 */
public class PageResp<T> {
    private Long total;
    private List<T> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
