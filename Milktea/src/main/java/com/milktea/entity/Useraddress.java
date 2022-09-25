package com.milktea.entity;

import lombok.Data;

@Data
public class Useraddress {

    //    地址id
    private Integer id;

    //    用户id
    private Integer user_id;

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
