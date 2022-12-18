package com.milktea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.milktea.entity.NowUser;
import com.milktea.entity.UserAddress;
import com.milktea.mapper.NowUserMapper;
import com.milktea.service.NowUserServuce;
import org.springframework.stereotype.Service;

@Service
public class NowUserServiceImpl extends ServiceImpl<NowUserMapper,NowUser> implements NowUserServuce {
    @Override
    public void setUserId(int id) {
        this.removeById(1);
        NowUser nowUser = new NowUser();
        nowUser.setId(1);
        nowUser.setUserid(id);
        this.save(nowUser);
    }

    public NowUser getUserId(){
        return this.getById(1);
    }



}
