package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.R;
import com.milktea.dto.DataGoodsDto;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.Dingdan;
import com.milktea.entity.Goods;
import com.milktea.entity.GoodsType;
import com.milktea.entity.Peiliao;
import com.milktea.service.DingdanService;
import com.milktea.service.GoodsService;
import com.milktea.service.GoodsTypeService;
import com.milktea.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data")
@Slf4j
public class keshihuaController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private DingdanService dingdanService;

    @GetMapping("/goods")
    public R<List<DataGoodsDto>> datagoods(){
        //构造查询条件
        LambdaQueryWrapper<GoodsType> queryWrapper = new LambdaQueryWrapper<>();

        //添加排序条件
        queryWrapper.orderByDesc(GoodsType::getUpdateTime);

        List<GoodsType> list = goodsTypeService.list(queryWrapper);

        List<DataGoodsDto> goodsDtoList = list.stream().map((item) -> {
            DataGoodsDto dataGoodsDto = new DataGoodsDto();
            dataGoodsDto.setName(item.getTypename());
            //构造查询条件
            LambdaQueryWrapper<Goods> queryWrapper2 = new LambdaQueryWrapper<>();

            //添加排序条件
            queryWrapper2.eq(Goods::getTypeId,item.getId());
            queryWrapper2.orderByDesc(Goods::getUpdateTime);

            List<Goods> list2 = goodsService.list(queryWrapper2);
            dataGoodsDto.setValue(list2.size());

            return dataGoodsDto;
        }).collect(Collectors.toList());

        return R.success(goodsDtoList);
    }

    @GetMapping("/goodsname")
    public R<List<String>> datagoodsname(){
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();

        //添加排序条件
        queryWrapper.orderByDesc(Goods::getUpdateTime);

        List<Goods> list = goodsService.list(queryWrapper);

        List<String> nameList = list.stream().map((item) -> {
            String name = item.getName();

            return name;
        }).collect(Collectors.toList());
        return R.success(nameList);
    }

    @GetMapping("/goodsnumber")
    public R<List<Integer>> datagoodsnumber(){
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();

        //添加排序条件
        queryWrapper.orderByDesc(Goods::getUpdateTime);

        List<Goods> list = goodsService.list(queryWrapper);

        List<Integer> numberList = list.stream().map((item) -> {
            LambdaQueryWrapper<Dingdan> queryWrapper2 = new LambdaQueryWrapper<>();

            //添加排序条件
            queryWrapper2.eq(Dingdan::getGoodsId,item.getId());
            List<Dingdan> dingdanlist = dingdanService.list(queryWrapper2);

            return dingdanlist.size();
        }).collect(Collectors.toList());
        return R.success(numberList);
    }

}
