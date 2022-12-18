package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.UserAddress;
import com.milktea.mapper.UseraddressMapper;
import com.milktea.service.UseraddressService;
import org.springframework.stereotype.Service;

@Service
public class UseraddressServiceImpl extends ServiceImpl<UseraddressMapper, UserAddress> implements UseraddressService {
}
