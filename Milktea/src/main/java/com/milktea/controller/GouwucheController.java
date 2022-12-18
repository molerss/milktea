package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.BaseContext;
import com.milktea.common.R;
import com.milktea.dto.GoodsDto;
import com.milktea.dto.GoodsGouwucheDto;
import com.milktea.dto.GoodsIndexDto;
import com.milktea.entity.Goods;
import com.milktea.entity.GoodsType;
import com.milktea.entity.Shoppingcart;
import com.milktea.service.GoodsService;
import com.milktea.service.NowUserServuce;
import com.milktea.service.ShoppingcartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gouwuche")
@Slf4j
public class GouwucheController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private NowUserServuce nowUserServuce;

    @Autowired
    private ShoppingcartService shoppingcartService;

    @PostMapping
    public R<String> save(HttpSession session , @RequestBody Goods goods) {

        Shoppingcart shoppingcart = new Shoppingcart();

        UUID ids = UUID.randomUUID();
        Integer userId = ids.toString().hashCode();
        shoppingcart.setId(userId);

        shoppingcart.setGoodsId(goods.getId());

        shoppingcart.setUserId(nowUserServuce.getUserId().getUserid());

        shoppingcartService.save(shoppingcart);

        return R.success("添加成功");
    }

    @GetMapping
    private R<List<GoodsGouwucheDto>> get(){

        //条件构造器
        LambdaQueryWrapper<Shoppingcart> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByDesc(Shoppingcart::getUpdateTime);
        queryWrapper.eq(Shoppingcart::getUserId,nowUserServuce.getUserId().getUserid());

        List<Shoppingcart> list = shoppingcartService.list(queryWrapper);

        List<GoodsGouwucheDto> goodsIndexDtoList = list.stream().map((item) -> {
            GoodsGouwucheDto goodsGouwucheDto = new GoodsGouwucheDto();
            Goods goods = goodsService.getById(item.getGoodsId());
            BeanUtils.copyProperties(goods, goodsGouwucheDto);
            goodsGouwucheDto.setGouwuid(item.getId());
            return goodsGouwucheDto;
        }).collect(Collectors.toList());

        return R.success(goodsIndexDtoList);
    }

    @DeleteMapping
    private R<String> delete(int id){
        shoppingcartService.removeById(id);
        return R.success("删除成功");
    }

    @DeleteMapping("/piliang")
    public R<String> delete(String ids) {
//        Integer[] list = Convert.toIntArray(ids.split(","));

        String[] arr = ids.split(",");
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = Integer.parseInt(arr[i]);
        }
        List<Integer> list1 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        shoppingcartService.removeByIds(list1);

        return R.success("删除菜品成功");
    }

}
