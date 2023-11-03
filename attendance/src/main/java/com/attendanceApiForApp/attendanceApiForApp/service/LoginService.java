package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.dto.LoginDto;
import com.attendanceApiForApp.attendanceApiForApp.dto.UserAuthDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LoginService {

    private final WebClient.Builder webClientBuilder;


    public LoginService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl("http://192.168.1.9:9090");
    }

    public Mono<UserAuthDto> authLogin(LoginDto loginDto) {
        return webClientBuilder.build().post()
                .uri("/auth/login")
                .bodyValue(loginDto)
                .retrieve()
                .bodyToMono(UserAuthDto.class);
    }
}
