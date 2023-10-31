package com.bricknet.apigateway.service;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class JwtMap {
    private WebClient.Builder webClientBuilder;

    private final AtomicReference<String> mm = new AtomicReference<>();

    public JwtMap(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder.baseUrl("http://192.168.1.9:8083/auth");
    }


//    public Mono<String> getByjwt(String empcode) {
//        return webClientBuilder.build().get()
//                .uri( uriBuilder -> uriBuilder.path("/checkJwt").queryParam(empcode).build())
//                .retrieve()
//                .bodyToMono(String.class);
//    }
    public Mono<String> getByjwt(String empcode) {
        Mono<String> result = webClientBuilder.build().get()
                .uri("/checkJwt?empcode="+empcode)
                .retrieve()
                .bodyToMono(String.class);
        setToken(result);
        return result;
    }

    public void setToken(Mono<String> result){
        result.subscribe(res -> {
            mm.set(res);
        });
    }

    public String getTokenFromAuth(){
        return mm.get();
    }


}