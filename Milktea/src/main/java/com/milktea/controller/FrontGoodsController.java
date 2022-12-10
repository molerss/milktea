package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.R;
import com.milktea.dto.GoodsDto;
import com.milktea.dto.GoodsIndexDto;
import com.milktea.entity.Goods;
import com.milktea.entity.GoodsType;
import com.milktea.service.GoodsService;
import com.milktea.service.GoodsTypeService;
import com.milktea.service.PeiliaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/frontgoods")
@Slf4j
public class FrontGoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsTypeService typeService;

    @Autowired
    private PeiliaoService peiliaoService;

    @GetMapping()
    public R<List<GoodsIndexDto>> get(){
        //条件构造器
        LambdaQueryWrapper<GoodsType> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByAsc(GoodsType::getId).orderByDesc(GoodsType::getUpdateTime);

        List<GoodsType> list = typeService.list(queryWrapper);

        List<GoodsIndexDto> goodsIndexDtoList = list.stream().map((item) -> {
            GoodsIndexDto goodsIndexDto = new GoodsIndexDto();

            BeanUtils.copyProperties(item, goodsIndexDto);

            int typeId = item.getId();//分类Id

            //条件构造器
            LambdaQueryWrapper<Goods> queryWrapper2 = new LambdaQueryWrapper<>();
            //添加排序条件
            queryWrapper2.eq(Goods::getTypeId,typeId);
            queryWrapper2.orderByAsc(Goods::getId);

            List<Goods> list2 = goodsService.list(queryWrapper2);

            goodsIndexDto.setGoods(list2);

            return goodsIndexDto;
        }).collect(Collectors.toList());
        return R.success(goodsIndexDtoList);
    }
}
