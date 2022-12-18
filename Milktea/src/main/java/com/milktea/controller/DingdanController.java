package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.R;
import com.milktea.dto.DingdanDto;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.*;
import com.milktea.service.DingdanCodeService;
import com.milktea.service.DingdanService;
import com.milktea.service.GoodsService;
import com.milktea.service.NowUserServuce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dingdan")
@Slf4j
public class DingdanController {

    @Autowired
    private DingdanService dingdanService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private NowUserServuce nowUserServuce;

    @Autowired
    private DingdanCodeService dingdanCodeService;

    @PostMapping
    private R<String> save(String ids){

        String[] arr = ids.split(",");
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = Integer.parseInt(arr[i]);
        }
        List<Integer> list1 = Arrays.stream(arr2).boxed().collect(Collectors.toList());

        int code = (int)((Math.random()*9+1)*100000);
        String code2 = String.valueOf(code);

        DingdanCode dingdanCode = new DingdanCode();
        dingdanCode.setUserId(nowUserServuce.getUserId().getUserid());
        dingdanCode.setCode(code2);
        UUID uids2 = UUID.randomUUID();
        Integer dingdanId = uids2.toString().hashCode();
        dingdanCode.setId(dingdanId);
        dingdanCode.setState(0);
        dingdanCode.setAddressId(nowUserServuce.getUserId().getAddressid());
        dingdanCodeService.save(dingdanCode);

        List<Dingdan> list = list1.stream().map((item) -> {
            Dingdan dingdan = new Dingdan();

            UUID uids = UUID.randomUUID();
            Integer userId = uids.toString().hashCode();
            dingdan.setId(userId);

            dingdan.setState(0);

            dingdan.setUserId(nowUserServuce.getUserId().getUserid());

            dingdan.setGoodsId(item);

            dingdan.setCode(code2);
            return dingdan;
        }).collect(Collectors.toList());

        dingdanService.saveBatch(list);

        return R.success("插入成功");
    }

    @GetMapping
    private R<List<DingdanDto>> get(){
        int id = nowUserServuce.getUserId().getUserid();


        //构造查询条件
        LambdaQueryWrapper<DingdanCode> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DingdanCode::getUserId,id);
        //添加排序条件
        queryWrapper.orderByDesc(DingdanCode::getUpdateTime);

        List<DingdanCode> list = dingdanCodeService.list(queryWrapper);

        List<DingdanDto> goodsDtoList = list.stream().map((item) -> {
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
        return R.success(goodsDtoList);
    }


}
