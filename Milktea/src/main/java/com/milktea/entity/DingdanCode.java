package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DingdanCode {

    //    订单id
    private Integer id;

    //    用户id
    private Integer userId;

    //    地址id
    private Integer addressId;

    //    订单编号
    private String code;

    //    订单状态   0：未取  1；已取
    private Integer state;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
