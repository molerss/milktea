package com.milktea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.User;
import com.milktea.mapper.UserMapper;
import com.milktea.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
