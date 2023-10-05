package com.example.SuperAdmin.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCredential {
    private String employeeName;
    private String uuid;
    private String employeeCode;
    private String password;
    private String role;
    private String companyEmail;

}
