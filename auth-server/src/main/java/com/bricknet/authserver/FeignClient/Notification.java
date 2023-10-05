package com.bricknet.authserver.FeignClient;

import com.bricknet.authserver.Dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification/send")
public interface Notification {
    @PostMapping("/email")
    public ResponseEntity<String> sendEmailNotification(
            @RequestBody NotificationDto notification);
}
