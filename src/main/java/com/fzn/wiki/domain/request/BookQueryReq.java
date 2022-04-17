package com.fzn.wiki.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookQueryReq extends PageReq {
    private Long id;

    private String name;
}