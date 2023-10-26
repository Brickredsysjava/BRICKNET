package com.bricknet.authserver.FeignClient;

import com.bricknet.authserver.Dto.ForgetPassword;
import com.bricknet.authserver.Dto.UserAuthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserProfile {

    private final WebClient.Builder webClientBuilder;

    public UserProfile(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl("http://192.168.1.9:9090");
    }

//    public void UserProfileWebClient(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("http://localhost:8081 /user/profile").build();
//    }

//    public UserProfile(WebClient webClient) {
//        this.webClient = webClient;
//    }

    public Mono<UserAuthInfo> getByUserName(String username) {
        return webClientBuilder.build().get()
                .uri(uriBuilder -> uriBuilder.path("/user/profile/profileFromUserName")
                        .queryParam("username", username)
                        .build())
                .retrieve()
                .bodyToMono(UserAuthInfo.class);
    }

    public Mono<UserAuthInfo> passwordUpdate(ForgetPassword forgetPassword) {
        return webClientBuilder
                .build().post().uri(uriBuilder ->uriBuilder.path("/user/profile/passwordUpdate")
                        .build())
                .bodyValue(forgetPassword)
                .retrieve()
                .bodyToMono(UserAuthInfo.class);
    }


}
