package com.fzn.wiki.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author FZN
 * @date 2022/04/05 14:14
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private Integer id;
    private String name;
    private String password;
}
