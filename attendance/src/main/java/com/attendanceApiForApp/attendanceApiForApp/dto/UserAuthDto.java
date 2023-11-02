package com.attendanceApiForApp.attendanceApiForApp.dto;

import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {

    private String jwtTokens;

    private String username;
    private String role;
    private String emailId;

    private String userId;
    private String empCode;

}
