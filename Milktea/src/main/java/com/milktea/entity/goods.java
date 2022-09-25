package com.milktea.entity;

import lombok.Data;

@Data
public class goods {

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
    private Integer type_id;
}
