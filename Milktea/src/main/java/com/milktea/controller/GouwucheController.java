package com.milktea.controller;

import com.milktea.common.BaseContext;
import com.milktea.common.R;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.Goods;
import com.milktea.entity.Shoppingcart;
import com.milktea.service.GoodsService;
import com.milktea.service.ShoppingcartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/gouwuche")
@Slf4j
public class GouwucheController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShoppingcartService shoppingcartService;

    @PostMapping
    public R<String> save(@RequestBody Goods goods) {

        Shoppingcart shoppingcart = new Shoppingcart();

        UUID ids = UUID.randomUUID();
        Integer userId = ids.toString().hashCode();
        shoppingcart.setId(userId);

        shoppingcart.setGoodsId(goods.getId());

        Integer b= BaseContext.getCurrentId();
        shoppingcart.setUserId(b);

        shoppingcartService.save(shoppingcart);

        return R.success("添加成功");
    }
}
