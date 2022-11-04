package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milktea.common.R;
import com.milktea.entity.Goods;
import com.milktea.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name) {

        //构造分页构造器对象
        Page<Goods> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null,Goods::getName,name);
        //添加排序条件，根据UpdateTime进行排序
        queryWrapper.orderByAsc(Goods::getUpdateTime);
        //分页查询
        goodsService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    @DeleteMapping
    public R<String> delete(String ids){
//        Integer[] list = Convert.toIntArray(ids.split(","));


        String[] arr = ids.split(",");
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = Integer.parseInt(arr[i]);
        }
        List<Integer> list1 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        goodsService.removeByIds(list1);

        return R.success("新增菜品成功");
    }
}
