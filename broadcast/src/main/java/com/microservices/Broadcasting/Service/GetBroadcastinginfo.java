package com.microservices.Broadcasting.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GetBroadcastinginfo {

    private WebClient.Builder webClientBuilder;


    public GetBroadcastinginfo(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public String getBroadcastinginfoMethod(){
        return webClientBuilder.baseUrl("http://localhost:8096/send/")
                .build().get().uri("/test").retrieve().
                bodyToMono(String.class).block();
    }
}
