package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.model.Employee;
import com.attendanceApiForApp.attendanceApiForApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Endpoint to get all employees
    @GetMapping("/getAllEmployees")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Endpoint to get a single employee by ID
    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to create a new employee
    @PostMapping("/createEmp")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    // Endpoint to update an existing employee
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete an employee by ID
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getLogin/{emp_id}/{password}")
    public ResponseEntity<String> getLastTransactionId(@PathVariable String emp_id, @PathVariable String password) {
        String res = employeeService.loginApi(emp_id, password);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

