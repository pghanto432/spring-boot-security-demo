package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(Employee emp);

    List<Employee> getAllEmployees();

    Optional<Employee> getById(Long id);
}
