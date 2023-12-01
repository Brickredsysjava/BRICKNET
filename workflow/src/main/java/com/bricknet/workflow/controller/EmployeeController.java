package com.bricknet.workflow.controller;

import com.bricknet.workflow.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        // Create a list of 15 sample employees
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("E001", "John", "Doe"));
        employees.add(new Employee("E002", "Jane", "Smith"));
        employees.add(new Employee("E003", "Bob", "Johnson"));
        employees.add(new Employee("E004", "Alice", "Williams"));
        employees.add(new Employee("E005", "David", "Brown"));
        employees.add(new Employee("E006", "Emily", "Johnson"));
        employees.add(new Employee("E007", "Michael", "Smith"));
        employees.add(new Employee("E008", "Sarah", "Davis"));
        employees.add(new Employee("E009", "Chris", "Anderson"));
        employees.add(new Employee("E010", "Jessica", "Moore"));
        employees.add(new Employee("E011", "Kevin", "Wilson"));
        employees.add(new Employee("E012", "Linda", "Taylor"));
        employees.add(new Employee("E013", "Ryan", "Martin"));
        employees.add(new Employee("E014", "Karen", "Hall"));
        employees.add(new Employee("E015", "Tom", "Clark"));
        return employees;
    }
    @GetMapping("/employeeName")
    public String getEmployeeName(@RequestParam String empID) {
        // Create a list of 15 sample employees
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("E001", "John", "Doe"));
        employees.add(new Employee("E002", "Jane", "Smith"));
        employees.add(new Employee("E003", "Bob", "Johnson"));
        employees.add(new Employee("E004", "Alice", "Williams"));
        employees.add(new Employee("E005", "David", "Brown"));
        employees.add(new Employee("E006", "Emily", "Johnson"));
        employees.add(new Employee("E007", "Michael", "Smith"));
        employees.add(new Employee("E008", "Sarah", "Davis"));
        employees.add(new Employee("E009", "Chris", "Anderson"));
        employees.add(new Employee("E010", "Jessica", "Moore"));
        employees.add(new Employee("E011", "Kevin", "Wilson"));
        employees.add(new Employee("E012", "Linda", "Taylor"));
        employees.add(new Employee("E013", "Ryan", "Martin"));
        employees.add(new Employee("E014", "Karen", "Hall"));
        employees.add(new Employee("E015", "Tom", "Clark"));
        Employee e1 = new Employee();
         employees.forEach(i->{
            i.getId().equals(empID);
            e1.setFirstName(i.getFirstName());
            e1.setLastName(i.getLastName());
        } );
        System.out.println(e1.toString());

      return  e1.getFirstName()+" "+e1.getLastName();
    }
}





