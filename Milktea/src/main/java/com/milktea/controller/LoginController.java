package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.R;
import com.milktea.entity.User;
import com.milktea.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/vuelogin")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {

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
            request.getSession().setAttribute("user",user1.getId());
            return R.success(user1);
        }
    }

}
