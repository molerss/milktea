package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.Shoppingcart;
import com.milktea.mapper.ShoppingcartMapper;
import com.milktea.service.ShoppingcartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingcartServiceImpl extends ServiceImpl<ShoppingcartMapper, Shoppingcart> implements ShoppingcartService {
}
