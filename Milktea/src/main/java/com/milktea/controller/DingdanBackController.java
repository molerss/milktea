package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milktea.common.R;
import com.milktea.dto.DingdanDto;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.Dingdan;
import com.milktea.entity.DingdanCode;
import com.milktea.entity.Goods;
import com.milktea.service.DingdanCodeService;
import com.milktea.service.DingdanService;
import com.milktea.service.GoodsService;
import com.milktea.service.NowUserServuce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dingdanback")
@Slf4j
public class DingdanBackController {

    @Autowired
    private DingdanService dingdanService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private NowUserServuce nowUserServuce;

    @Autowired
    private DingdanCodeService dingdanCodeService;

    @GetMapping("/page")
    private R<Page> page(int page, int pageSize, String name){
        //构造分页构造器对象
        Page<DingdanCode> pageInfo = new Page<>(page, pageSize);
        Page<DingdanDto> dingdanDtoPage = new Page<>();
        int state = Integer.parseInt(name);
        //条件构造器
        LambdaQueryWrapper<DingdanCode> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null, DingdanCode::getState, state);
        //添加排序条件，根据UpdateTime进行排序
        queryWrapper.orderByAsc(DingdanCode::getUpdateTime);
        //分页查询
        dingdanCodeService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dingdanDtoPage, "records");

        List<DingdanCode> records = pageInfo.getRecords();

        List<DingdanDto> list = records.stream().map((item) -> {
            DingdanDto dingdanDto = new DingdanDto();

            BeanUtils.copyProperties(item, dingdanDto);
            //构造查询条件
            LambdaQueryWrapper<Dingdan> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Dingdan::getCode,item.getCode());
            queryWrapper1.eq(Dingdan::getUserId,item.getUserId());
            //添加排序条件
            queryWrapper1.orderByDesc(Dingdan::getUpdateTime);

            List<Dingdan> list1 = dingdanService.list(queryWrapper1);

            List<Goods> goodsList = list1.stream().map((item1) -> {
                Goods goods = new Goods();

                goods = goodsService.getById(item1.getGoodsId());

                return goods;
            }).collect(Collectors.toList());

            double num =0;
            for (int i =0;i<goodsList.size();i++){
                num += goodsList.get(i).getPrice();
            }

            dingdanDto.setNum(num);

            dingdanDto.setGoods(goodsList);

            dingdanDto.setLengths(goodsList.size());

            return dingdanDto;
        }).collect(Collectors.toList());

        dingdanDtoPage.setRecords(list);
        return R.success(dingdanDtoPage);
    }

    @PutMapping
    private R<String> update(String ids){

        int id = Integer.parseInt(ids);

        DingdanCode dingdanCode = dingdanCodeService.getById(id);

        if (dingdanCode.getState() == 0){
            dingdanCode.setState(1);
        }else {
            dingdanCode.setState(3);
        }

        dingdanCodeService.updateById(dingdanCode);
        return R.success("操作完成");
    }

}
