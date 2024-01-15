package com.bricknet.workflow.service;

import com.bricknet.workflow.dto.NotificationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NotificationServiceImpl  implements  NotificationService{

    private final WebClient.Builder webClientBuilder ;

    String email = "karl98perfect@gmail.com";

    String baseUrl = "https://192 .168.1.50 " ;

    public NotificationServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public void sendMail(String to, String subject, String text) {

    }

    public Mono<NotificationDto> fetchDataFromApi(){
        return  webClientBuilder.baseUrl(baseUrl).build().get()
                .uri("/send/getNotification/")
                .retrieve().bodyToMono(NotificationDto.class);
    }

}
