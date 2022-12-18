package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.R;
import com.milktea.dto.GoodsDto;
import com.milktea.entity.DingdanCode;
import com.milktea.entity.NowUser;
import com.milktea.entity.UserAddress;
import com.milktea.service.NowUserServuce;
import com.milktea.service.UseraddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    private NowUserServuce nowUserServuce;

    @Autowired
    private UseraddressService useraddressService;

    @PostMapping
    private R<String> save(UserAddress userAddress){

        UUID ids = UUID.randomUUID();
        Integer userId = ids.toString().hashCode();
        userAddress.setId(userId);
        userAddress.setUserId(nowUserServuce.getUserId().getUserid());
        userAddress.setCode("0");

        useraddressService.save(userAddress);
        return R.success("添加成功");
    }

    @GetMapping
    private R<List<UserAddress>> get(){
        //构造查询条件
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getUserId,nowUserServuce.getUserId().getUserid());
        //添加排序条件
        queryWrapper.orderByDesc(UserAddress::getUpdateTime);

        List<UserAddress> list = useraddressService.list(queryWrapper);
        return R.success(list);
    }

    @GetMapping("one")
    private R<UserAddress> getone(){

        //构造查询条件
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getId,nowUserServuce.getUserId().getAddressid());
        //添加排序条件
        queryWrapper.orderByDesc(UserAddress::getUpdateTime);

        UserAddress address = useraddressService.getOne(queryWrapper);
        return R.success(address);
    }

    @GetMapping("/{id}")
    private R<UserAddress> getones(@PathVariable String id){

        //构造查询条件
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        int Id = Integer.parseInt(id);
        queryWrapper.eq(UserAddress::getId,Id);

        UserAddress address = useraddressService.getOne(queryWrapper);
        return R.success(address);
    }

    @PutMapping("/{id}")
    public R<String> update(@PathVariable String id) {
        NowUser nowUser = new NowUser();
        int Id = Integer.parseInt(id);
        nowUser.setAddressid(Id);
        nowUser.setUserid(nowUserServuce.getUserId().getUserid());

        nowUser.setId(1);

        int idd = nowUserServuce.getUserId().getUserid();

        //构造查询条件
        LambdaQueryWrapper<NowUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NowUser::getUserid,idd);


        nowUserServuce.remove(queryWrapper);
        nowUserServuce.save(nowUser);

        return R.success("修改地址成功");
    }
}
