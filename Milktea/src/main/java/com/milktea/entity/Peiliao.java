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


}
