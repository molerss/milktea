package com.milktea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.NowUser;

public interface NowUserServuce extends IService<NowUser> {

    public void setUserId(int id);

    public NowUser getUserId();

}
