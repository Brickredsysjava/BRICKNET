package com.bricknet.authserver.FeignClient;

import com.bricknet.authserver.Dto.ForgetPassword;
import com.bricknet.authserver.Dto.NotificationDto;
import com.bricknet.authserver.Dto.UserAuthInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class Notification {

    private final WebClient.Builder webClientBuilder;

    public Notification(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<NotificationDto> sendEmailNotification(NotificationDto notificationDto){
        return webClientBuilder.build().post().uri(uriBuilder -> uriBuilder.path("/send/email").build())
                .bodyValue(notificationDto).retrieve().bodyToMono(NotificationDto.class);
    }



//    @PostMapping("/email")
//    public ResponseEntity<String> sendEmailNotification(
//            @RequestBody NotificationDto notification);
}
