package com.milktea.dto;

import com.milktea.entity.Goods;
import com.milktea.entity.GoodsType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GoodsIndexDto extends GoodsType {

    private List<Goods> goods = new ArrayList<>();

}
