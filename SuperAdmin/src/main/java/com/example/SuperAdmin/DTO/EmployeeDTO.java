package com.example.SuperAdmin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String auto_id;

    private String name;

    private String email;

    private String password;

    private String emp_id;
}
