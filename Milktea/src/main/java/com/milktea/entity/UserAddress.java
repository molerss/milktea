package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 地址信息表
 */
@Data
public class UserAddress {

    //    地址id
    private Integer id;

    //    用户id
    private Integer userId;//采用驼峰命名法进行映射

    //    用户姓名
    private String name;

    //    用户性别
    private String sex;

    //    用户地址
    private String address;

    //    用户电话
    private String phone;

    //    地址标签
    private String code;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
