package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milktea.common.R;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.Goods;
import com.milktea.entity.GoodsType;
import com.milktea.entity.Peiliao;
import com.milktea.service.GoodsService;
import com.milktea.service.GoodsTypeService;
import com.milktea.service.PeiliaoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Autowired
    private PeiliaoService peiliaoService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        //构造分页构造器对象
        Page<Goods> pageInfo = new Page<>(page, pageSize);
        Page<GoodsDto> goodsDtoPage = new Page<>();
        //条件构造器
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null, Goods::getName, name);
        //添加排序条件，根据UpdateTime进行排序
        queryWrapper.orderByAsc(Goods::getUpdateTime);
        //分页查询
        goodsService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, goodsDtoPage, "records");

        List<Goods> records = pageInfo.getRecords();

        List<GoodsDto> list = records.stream().map((item) -> {
            GoodsDto goodsDto = new GoodsDto();

            BeanUtils.copyProperties(item, goodsDto);

            int typeId = item.getTypeId();//分类Id
            //根据id查询分类对象
            GoodsType goodsType = goodsTypeService.getById(typeId);

            if (goodsType != null) {
                String TypeName = goodsType.getTypename();
                goodsDto.setGoodsTypeName(TypeName);
            }


            return goodsDto;
        }).collect(Collectors.toList());

        goodsDtoPage.setRecords(list);
        return R.success(goodsDtoPage);
    }

    @GetMapping("/list")
    public R<List<GoodsDto>> get(Goods goods) {

        //构造查询条件
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(goods.getTypeId() != null, Goods::getTypeId, goods.getTypeId());

        //添加排序条件
        queryWrapper.orderByDesc(Goods::getUpdateTime);

        List<Goods> list = goodsService.list(queryWrapper);

        List<GoodsDto> goodsDtoList = list.stream().map((item) -> {
            GoodsDto goodsDto = new GoodsDto();

            BeanUtils.copyProperties(item, goodsDto);

            int typeId = item.getTypeId();//分类Id
            //根据id查询分类对象
            GoodsType goodsType = goodsTypeService.getById(typeId);

            if (goodsType != null) {
                String TypeName = goodsType.getTypename();
                goodsDto.setGoodsTypeName(TypeName);
            }

            //当前菜品id
            int goodsId = item.getId();
            LambdaQueryWrapper<Peiliao> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Peiliao::getGoodsid, goodsId);
            List<Peiliao> peiliaoList = peiliaoService.list(lambdaQueryWrapper);
            goodsDto.setPeiliao(peiliaoList);
            return goodsDto;
        }).collect(Collectors.toList());

        return R.success(goodsDtoList);
    }

    @GetMapping("/{id}")
    public R<GoodsDto> get(@PathVariable int id){

        GoodsDto dishDto = goodsService.getByIdWithPeiliao(id);
        return R.success(dishDto);
    }

    @DeleteMapping
    public R<String> delete(String ids) {
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

    @PostMapping
    public R<String> save(@RequestBody GoodsDto goodsDto) {

        UUID ids = UUID.randomUUID();
        Integer userId = ids.toString().hashCode();
        goodsDto.setId(userId);
        goodsService.saveWithPeiliao(goodsDto);

        return R.success("新增菜品成功");
    }

    @PutMapping
    public R<String> update(@RequestBody GoodsDto goodsDto) {

        goodsService.updateWithPeiliao(goodsDto);

        return R.success("修改菜品成功");
    }
}
