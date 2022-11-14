package com.milktea.dto;

import com.milktea.entity.Goods;
import com.milktea.entity.Peiliao;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GoodsDto extends Goods {

    private List<Peiliao> peiliao = new ArrayList<>();

    private String GoodsTypeName;
}
