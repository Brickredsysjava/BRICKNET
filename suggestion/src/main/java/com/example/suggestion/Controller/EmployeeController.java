package com.example.suggestion.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/get")
    public String getEmployeeName(){
        return "piyush";
    }

}
