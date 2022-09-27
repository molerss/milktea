package com.milktea.controller;

import com.milktea.common.R;
import com.milktea.entity.Employee;
import com.milktea.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        @GetMapping
        public R<List<Employee>> GetEmployee(){

            List<Employee> list = employeeService.list();

            System.out.println(employeeService.list());
            System.out.println(list);
            return R.success(list);
        }
}
