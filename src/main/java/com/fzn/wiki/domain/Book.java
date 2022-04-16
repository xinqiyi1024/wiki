package com.fzn.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 电子书
 * </p>
 *
 * @author fzn
 * @since 2022-04-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_book")
@ApiModel(value = "Book对象", description = "电子书")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId("id")
    private Long id;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("分类1")
    @TableField("category1_id")
    private Long category1Id;

    @ApiModelProperty("分类2")
    @TableField("category2_id")
    private Long category2Id;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("封面")
    @TableField("cover")
    private String cover;

    @ApiModelProperty("文档数")
    @TableField("doc_count")
    private Integer docCount;

    @ApiModelProperty("阅读数")
    @TableField("view_count")
    private Integer viewCount;

    @ApiModelProperty("点赞数")
    @TableField("vote_count")
    private Integer voteCount;

    @ApiModelProperty("逻辑删除")
    @TableField("is_delete")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @ApiModelProperty("修改时间")
    @TableField("gmt_update")
    private LocalDateTime gmtUpdate;


}
