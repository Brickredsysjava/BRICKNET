package com.example.SuperAdmin.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPassword {

    @NotBlank(message = "username is required")
    @NotNull(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    @NotNull(message = "password is required")
    private String password;

}
