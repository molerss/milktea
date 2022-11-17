package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

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
//   身份证号
    private String idcard;

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
