package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milktea.common.R;
import com.milktea.entity.Goods;
import com.milktea.entity.GoodsType;
import com.milktea.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 分类管理
 */

@RestController
@RequestMapping("/goodstype")
public class TypesController {

    @Autowired
    private GoodsTypeService typeService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        //分页构造器
        Page<GoodsType> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<GoodsType> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(GoodsType::getUpdateTime);

        //分页查询
        typeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 查询所有分类
     * @param
     * @return
     */
    @GetMapping("/list")
    public R<List<GoodsType>> list(){
        //条件构造器
        LambdaQueryWrapper<GoodsType> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序条件
        queryWrapper.orderByAsc(GoodsType::getId).orderByDesc(GoodsType::getUpdateTime);

        List<GoodsType> list = typeService.list(queryWrapper);
        return R.success(list);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(int id){
        typeService.removeById(id);
        return R.success("分类信息删除成功");
    }

    @PostMapping
    public R<String> save(@RequestBody GoodsType goods){

        UUID ids = UUID.randomUUID();
        Integer userId = ids.toString().hashCode();
        goods.setId(userId);

        typeService.save(goods);

        return R.success("新增菜品成功");
    }

    @PutMapping
    public R<String> update(@RequestBody GoodsType goods){

        typeService.updateById(goods);

        return R.success("修改菜品成功");
    }
}
