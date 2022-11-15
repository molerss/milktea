package com.milktea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.Goods;
import com.milktea.entity.Peiliao;
import com.milktea.mapper.GoodsMapper;
import com.milktea.service.GoodsService;
import com.milktea.service.PeiliaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private PeiliaoService peiliaoService;

    public void saveWithPeiliao(GoodsDto goodsDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(goodsDto);

        int goodsId = goodsDto.getId();//菜品ID

        //菜品口味
        List<Peiliao> flavors = goodsDto.getPeiliao();
        flavors = flavors.stream().map((item) -> {
            item.setGoodsid(goodsId);
            UUID ids = UUID.randomUUID();
            Integer userId = ids.toString().hashCode();
            item.setId(userId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味数据到菜品口味表dish_flavor
        peiliaoService.saveBatch(flavors);
    }

    @Override
    public GoodsDto getByIdWithPeiliao(int id) {
        //查询菜品基本信息，从dish表查询
        Goods goods = this.getById(id);

        GoodsDto goodsDto = new GoodsDto();
        BeanUtils.copyProperties(goods,goodsDto);

        //查询当前菜品对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<Peiliao> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Peiliao::getGoodsid,goods.getId());
        List<Peiliao> list = peiliaoService.list(queryWrapper);
        goodsDto.setPeiliao(list);

        return goodsDto;
    }

    @Override
    public void updateWithPeiliao(GoodsDto goodsDto) {

        this.updateById(goodsDto);

        LambdaQueryWrapper<Peiliao> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Peiliao::getGoodsid,goodsDto.getId());

        peiliaoService.remove(queryWrapper);

        List<Peiliao> flavors = goodsDto.getPeiliao();
        flavors = flavors.stream().map((item) -> {
            item.setGoodsid(goodsDto.getId());
            UUID ids = UUID.randomUUID();
            Integer userId = ids.toString().hashCode();
            item.setId(userId);
            return item;
        }).collect(Collectors.toList());

        peiliaoService.saveBatch(flavors);
    }
}
