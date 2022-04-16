package com.fzn.wiki.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author FZN
 * @date 2022/04/11 16:22
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResp<T> {
    private Long total;
    private List<T> list;
}
