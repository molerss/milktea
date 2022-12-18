package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.BaseContext;
import com.milktea.common.R;
import com.milktea.entity.User;
import com.milktea.service.NowUserServuce;
import com.milktea.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("/vuelogin")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private NowUserServuce nowUserServuce;

    @PostMapping("/login")
    public R<User> login( HttpSession session ,@RequestBody User user) {

//        UUID id = UUID.randomUUID();
//        Integer ids = id.toString().hashCode();
//        user.setId(ids);
         String username=user.getUsername();

        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user1=userService.getOne(queryWrapper);
        if(user1==null){
            return  R.error("登录失败");
        }else if(!user1.getPassword().equals(user.getPassword())){
            return R.error("密码错误");
        }else{
            nowUserServuce.setUserId(user1.getId());

            return R.success(user1);
        }
    }

    @PostMapping("/register")
    public R<String> login(@RequestBody User user){
        String username=user.getUsername();
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user1=userService.getOne(queryWrapper);
        if(user1 != null){
            return  R.error("用户名存在，重新输入");
        }else {
            UUID uuid = UUID.randomUUID();
            Integer userId = uuid.toString().hashCode();
            user.setId(userId);
            userService.save(user);
            return R.success("注册成功");
        }

    }


}
