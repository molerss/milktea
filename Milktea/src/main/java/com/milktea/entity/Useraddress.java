package com.milktea.entity;

import lombok.Data;

/**
 * 地址信息表
 */
@Data
public class Useraddress {

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
}
