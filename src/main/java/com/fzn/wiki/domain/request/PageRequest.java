package com.fzn.wiki.domain.request;

/**
 * @author FZN
 * @date 2022/04/11 16:22
 * @description
 */
public class PageRequest {
    private Integer page;
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}
