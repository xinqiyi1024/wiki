package com.fzn.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@TableName("tb_category")
@ApiModel(value = "Category对象", description = "电子书")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId("id")
    private Long id;

    @ApiModelProperty("父id")
    @TableField("parent")
    private Long parent;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("顺序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("逻辑删除")
    @TableField("is_delete")
    private Boolean delete;

    @ApiModelProperty("创建时间")
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @ApiModelProperty("修改时间")
    @TableField("gmt_update")
    private LocalDateTime gmtUpdate;


}
