package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.Goods;
import com.milktea.mapper.GoodsMapper;
import com.milktea.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
