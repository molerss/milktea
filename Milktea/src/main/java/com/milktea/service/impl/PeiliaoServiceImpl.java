package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.Peiliao;
import com.milktea.mapper.PeiliaoMapper;
import com.milktea.service.PeiliaoService;
import org.springframework.stereotype.Service;

@Service
public class PeiliaoServiceImpl extends ServiceImpl<PeiliaoMapper, Peiliao> implements PeiliaoService {
}
