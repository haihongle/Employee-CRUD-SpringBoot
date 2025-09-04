package com.example.employeecrud.controller;

import com.example.employeecrud.model.Employee;
import com.example.employeecrud.service.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/employee")
public class EmployeeViewController {

    private final IEmployeeService employeeService;

    public EmployeeViewController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Danh sách nhân viên
    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee-list";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    // Lưu (cả thêm mới và update)
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        if (employee.getId() == null) {
            employeeService.addEmployee(employee);
        } else {
            employeeService.updateEmployee(employee.getId(), employee);
        }
        return "redirect:/view/employee/list";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getAllEmployees()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/view/employee/list";
    }
}
