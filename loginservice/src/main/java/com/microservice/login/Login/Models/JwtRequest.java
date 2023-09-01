package com.microservice.login.Login.Models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JwtRequest {

    private String email;

    private String password;
}
