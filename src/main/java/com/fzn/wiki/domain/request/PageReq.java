package com.fzn.wiki.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author FZN
 * @date 2022/04/11 16:22
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReq {
    @NotNull(message = "[页码]不能为空")
    private Integer page;

    @NotNull(message = "[每条页数]不能为空")
    @Max(value = 1000, message = "[每条页数]不能超过1000")
    private Integer size;
}