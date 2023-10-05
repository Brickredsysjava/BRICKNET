package com.bricknet.authserver.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthInfo {
    private String uuid;
    private String employeeName;
    private String employeeCode;
    private String password;
    private String role;
    private String companyEmail;

}
