package com.milktea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.Goods;

public interface GoodsService extends IService<Goods> {

    //新增菜品，同时插入商品对应的口味数据
    public void saveWithPeiliao(GoodsDto dishDto);

    //根据id查询商品信息和对应的口味信息
    public GoodsDto getByIdWithPeiliao(int id);

    //更新商品信息，同时更新对应的口味信息
    public void updateWithPeiliao(GoodsDto goodsDto);
}
