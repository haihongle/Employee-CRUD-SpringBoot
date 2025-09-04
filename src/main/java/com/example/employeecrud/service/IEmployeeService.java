package com.example.employeecrud.service;

import com.example.employeecrud.model.Employee;

import java.util.List;

public interface IEmployeeService {
Employee addEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
boolean deleteEmployee(Long id);
List<Employee> getAllEmployees();
}
