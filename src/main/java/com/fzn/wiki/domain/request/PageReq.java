package com.fzn.wiki.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author FZN
 * @date 2022/04/11 16:22
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReq {
    private Integer page;
    private Integer size;
}