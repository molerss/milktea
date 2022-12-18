package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.DingdanCode;
import com.milktea.mapper.DingdanCodeMapper;
import com.milktea.service.DingdanCodeService;
import org.springframework.stereotype.Service;

@Service
public class DingdanCodeServiceImpl extends ServiceImpl<DingdanCodeMapper, DingdanCode> implements DingdanCodeService {
}
