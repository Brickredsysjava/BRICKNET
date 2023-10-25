package com.bricknet.authserver.service;

import com.bricknet.authserver.Dto.NotificationDto;
import com.bricknet.authserver.FeignClient.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationService {

    private final WebClient.Builder webClientBuilder;

    public NotificationService( WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
    }
//    public void sendEmail(NotificationDto notificationDto){
//         notification.sendEmailNotification(notificationDto);
//    }

    public void sendEmailNotification(NotificationDto notificationDto){
        webClientBuilder.baseUrl("http://192.168.1.9:8084/").build()

                .post().uri(uriBuilder -> uriBuilder.path("send/email")
                        .build())
                .bodyValue(notificationDto).retrieve().toBodilessEntity();
    }

}
