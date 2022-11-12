package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品类型表
 */
@Data
public class GoodsType {

//    id
    private Integer id;

//    类型名
    private String typename;

//    类型内容
    private String typecontent;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //创建人id
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    //修改人id
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
