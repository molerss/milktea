package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

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

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
