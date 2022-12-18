package com.milktea.dto;

import com.milktea.entity.Dingdan;
import com.milktea.entity.DingdanCode;
import com.milktea.entity.Goods;
import com.milktea.entity.Peiliao;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DingdanDto extends DingdanCode {

    private List<Goods> goods = new ArrayList<>();

    private int lengths;

    private double num;
}
