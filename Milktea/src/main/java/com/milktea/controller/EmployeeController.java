package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.milktea.common.R;
import com.milktea.entity.Employee;
import com.milktea.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public R<List<Employee>> GetEmployee() {

        List<Employee> list = employeeService.list();

        System.out.println(employeeService.list());
        System.out.println(list);
        return R.success(list);
    }

    @PostMapping
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){

        String username = employee.getUsername();

//        设置条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,username);
        Employee emp = employeeService.getOne(queryWrapper);

        if (emp == null){
            return R.error("登入失败");
        }

        if (!emp.getPassword().equals(employee.getPassword())){
            return R.error("密码不正确");
        }

        if (emp.getState() == 0){
            return R.error("账号处于禁用状态");
        }

        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

}

