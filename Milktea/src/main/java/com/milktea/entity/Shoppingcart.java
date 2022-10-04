package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 购物车
 */
@Data
public class Shoppingcart {

//    购物车 id
    private Integer id;

//    用户id
    private Integer userId;

//    商品id
    private Integer goodsId;

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
