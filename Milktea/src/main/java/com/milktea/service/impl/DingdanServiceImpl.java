package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.Dingdan;
import com.milktea.mapper.DingdanMapper;
import com.milktea.service.DingdanService;
import org.springframework.stereotype.Service;

@Service
public class DingdanServiceImpl extends ServiceImpl<DingdanMapper, Dingdan> implements DingdanService {
}
