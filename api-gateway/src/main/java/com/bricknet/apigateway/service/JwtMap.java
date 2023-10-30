package com.bricknet.apigateway.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JwtMap {
    private WebClient.Builder webClientBuilder;

    public JwtMap(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl("http://localhost:8083/    auth");
    }


//    public Mono<String> getByjwt(String empcode) {
//        return webClientBuilder.build().get()
//                .uri( uriBuilder -> uriBuilder.path("/checkJwt").queryParam(empcode).build())
//                .retrieve()
//                .bodyToMono(String.class);
//    }
    public Mono<String> getByjwt(String empcode) {
        return webClientBuilder.build().get()
                .uri("/checkJwt?empcode="+empcode)
                .retrieve()
                .bodyToMono(String.class);
    }
}