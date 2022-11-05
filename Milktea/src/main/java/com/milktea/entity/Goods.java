package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品信息表
 */
@Data
public class Goods {
    private static final long serialVersionUID = 1L;

    //商品id
    private Integer id;

    //商品名
    private String name;

    //商品价格
    private double price;

    //商品数量
    private Integer number;

    //商品图片
    private String img;

    //商品简介
    private String message;

    //商品类型id
    private Integer typeId;

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
