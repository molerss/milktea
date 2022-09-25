package com.milktea.entity;

import lombok.Data;

/**
 * 管理员表
 */
@Data
public class Employee {

//    管理员id
    private Integer id;

//    账号
    private String username;

//    密码
    private String password;

//    角色
    private Integer role;

//    状态
    private Integer state;

//    姓名
    private String name;

//    性别
    private String sex;

//    电话
    private String phone;

}
