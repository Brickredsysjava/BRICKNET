package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.DTO.NotificationDTO;
import com.example.SuperAdmin.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.ServiceNotFoundException;

@Service
public class NotificationServiceImplementation implements NotificationService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void pushNotification (NotificationDTO notificationDTO) throws ServiceNotFoundException
    {
//        String jsonBody ="{\"key\": \"value\"}";
//        webClientBuilder.baseUrl("http://20.198.3.41:8080/send")
//                .build().post().uri("/email").bodyValue(notificationDTO).retrieve().toBodilessEntity().block();
    }
}
