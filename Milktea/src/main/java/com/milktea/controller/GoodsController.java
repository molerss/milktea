package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milktea.common.R;
import com.milktea.entity.Goods;
import com.milktea.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {

        //构造分页构造器对象
        Page<Goods> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件，根据UpdateTime进行排序
        queryWrapper.orderByAsc(Goods::getUpdateTime);
        //分页查询
        goodsService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
