package com.milktea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.milktea.common.R;
import com.milktea.entity.Employee;
import com.milktea.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
@Slf4j
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
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        String username = employee.getUsername();

//        设置条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, username);
        Employee emp = employeeService.getOne(queryWrapper);

        if (emp == null) {
            return R.error("登入失败");
        }

        if (!emp.getPassword().equals(employee.getPassword())) {
            return R.error("密码不正确");
        }

        if (emp.getState() == 0) {
            return R.error("账号处于禁用状态");
        }

        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    @GetMapping("/employeeGet")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Employee> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(name != null, Employee::getName,name);

        queryWrapper.orderByAsc(Employee::getCreateTime);

        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

   @PostMapping("/employeePost")
    public R<String> save(@RequestBody Employee employee){
       UUID ids=UUID.randomUUID();
       Integer userID=ids.toString().hashCode();
       employee.setId(userID);
       employeeService.save(employee);

       return R.success("新增用户成功");
   }

   @PutMapping
    public R<String> update(@RequestBody Employee employee){

        employeeService.update();

        return R.success("修改用户成功");
   }

   @GetMapping("/{id}")
    public R<Employee> get(@PathVariable Integer id){
      Employee employee=  employeeService.getById(id);
        return R.success(employee);
   }
    }



