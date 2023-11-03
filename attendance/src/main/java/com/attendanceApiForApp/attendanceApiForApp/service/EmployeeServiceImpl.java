package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.dto.LoginDto;
import com.attendanceApiForApp.attendanceApiForApp.dto.UserAuthDto;
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
    private final LoginService loginService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CustomQuerries customQuerries, LoginService loginService) {
        this.employeeRepository = employeeRepository;
        this.customQuerries = customQuerries;
        this.loginService = loginService;
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
        Employee savedEmployee = new Employee();
//        savedEmployee.setAuto_id(employee.getAuto_id());
        savedEmployee.setPassword(employee.getPassword());
        savedEmployee.setAuto_id(employee.getAuto_id());
        savedEmployee.setEmail(employee.getEmail());
        savedEmployee.setEmp_id(employee.getEmp_id());
        savedEmployee.setName(employee.getName());
        return employeeRepository.save(savedEmployee);
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
//        Employee employee = new Employee();
//        LoginDto loginDto = new LoginDto();
//        loginDto.setUsername(emp_id);
//        loginDto.setPassword(password);
//        UserAuthDto userAuthDto = loginService.authLogin(loginDto).block();
//        String auto_id = userAuthDto.getUserId();
//        employee.setAuto_id(auto_id);

//        if(auto_id != null){
//            return employee.getAuto_id();
//        }
        return customQuerries.loginApi(emp_id, password);
        //return "Invalid credential";
    }
}

