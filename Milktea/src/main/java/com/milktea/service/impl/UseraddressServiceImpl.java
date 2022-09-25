package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.Useraddress;
import com.milktea.mapper.UseraddressMapper;
import com.milktea.service.UseraddressService;
import org.springframework.stereotype.Service;

@Service
public class UseraddressServiceImpl extends ServiceImpl<UseraddressMapper, Useraddress> implements UseraddressService {
}
