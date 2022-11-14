package com.milktea.dto;

import com.milktea.entity.Goods;
import lombok.Data;

@Data
public class GoodsDto extends Goods {

    private String GoodsTypeName;
}
