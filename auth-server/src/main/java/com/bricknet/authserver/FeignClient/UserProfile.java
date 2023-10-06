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

    private WebClient.Builder webClientBuilder;

    public UserProfile(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl("http://192.168.1.9:8081/user/profile");
    }

//    public void UserProfileWebClient(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("http://localhost:8081 /user/profile").build();
//    }

//    public UserProfile(WebClient webClient) {
//        this.webClient = webClient;
//    }

    public Mono<UserAuthInfo> getByUserName(String username) {
        return webClientBuilder.build().get()
                .uri( "/profileFromUserName/"+username)
                .retrieve()
                .bodyToMono(UserAuthInfo.class);
    }

    public Mono<UserAuthInfo> passwordUpdate(ForgetPassword forgetPassword) {
        return webClientBuilder.baseUrl("http://localhost:8081/user/profile")
                .build().put().uri("passwordUpdate")
                .bodyValue(forgetPassword)
                .retrieve()
                .bodyToMono(UserAuthInfo.class);
    }


}
