package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee);
    boolean deleteEmployee(Long id);
}

