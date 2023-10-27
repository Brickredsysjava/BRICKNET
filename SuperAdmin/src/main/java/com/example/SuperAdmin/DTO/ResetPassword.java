package com.example.SuperAdmin.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResetPassword {

    @Valid
    @NotBlank(message = "username is required")
    @NotNull(message = "username is required")
    private String username;

    @Valid
    @NotBlank(message = "password is required")
    @NotNull(message = "password is required")
    private String password;

}
