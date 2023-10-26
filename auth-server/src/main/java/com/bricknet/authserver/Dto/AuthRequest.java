package com.bricknet.authserver.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @Valid
    @NotNull(message = "username can't be null")
    @NotBlank(message = "username can't be blank")
    @NotEmpty(message = "username can't be empty")
    private String username;


    @Valid
    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be blank")
    @NotEmpty(message = "password can't be empty")
    private String password;
}
