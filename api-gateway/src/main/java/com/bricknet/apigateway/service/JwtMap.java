package com.bricknet.apigateway.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JwtMap {
    private WebClient.Builder webClientBuilder;

    public JwtMap(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl("http://192.168.1.9:8083/auth");
    }


    public Mono<String> getByjwt(String jwt) {
        return webClientBuilder.build().get()
                .uri( "/checkJwt/"+jwt)
                .retrieve()
                .bodyToMono(String.class);
    }
}