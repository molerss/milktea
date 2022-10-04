package com.milktea.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 配料表
 */
@Data
public class Peiliao {

//    配料id
    private Integer id;

//    配料名
    private String peiliaoname;

//    配料内容
    private String peiliaocontent;

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
