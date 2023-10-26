package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.Employee;
import com.attendanceApiForApp.attendanceApiForApp.repository.CustomQuerries;
import com.attendanceApiForApp.attendanceApiForApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CustomQuerries customQuerries;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CustomQuerries customQuerries) {
        this.employeeRepository = employeeRepository;
        this.customQuerries = customQuerries;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee( String id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setAuto_id(id);
            return employeeRepository.save(employee);
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(String id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public String loginApi(String emp_id, String password ) {
        return customQuerries.loginApi(emp_id, password);
    }
}

