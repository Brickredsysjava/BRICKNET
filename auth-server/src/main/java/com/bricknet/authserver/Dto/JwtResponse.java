package com.bricknet.authserver.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private String jwtTokens;

    private String username;

    private String role;

    private String emailId;

    private String userId;

    private String empCode;

}
