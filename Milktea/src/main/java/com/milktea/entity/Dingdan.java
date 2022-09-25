package com.milktea.entity;

import lombok.Data;

/**
 * 订单表
 */
@Data
public class Dingdan {

//    订单id
    private Integer id;

//    用户id
    private Integer userId;

//    商品id
    private Integer goodsId;

//    订单状态   0：未取  1；已取
    private Integer state;

//    订单编号
    private String code;
}
