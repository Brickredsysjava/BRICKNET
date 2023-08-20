package com.notification.Notification.Controller;

import com.notification.Notification.Dto.NotificationDTO;
import com.notification.Notification.Entity.Notification;
import com.notification.Notification.Service.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/send")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmailNotification(
            @RequestBody Notification notification) {
        String recipient = notification.getRecipient();
        String message = notification.getMessage();
        notificationService.sendEmailNotification(recipient, message);
        return ResponseEntity.ok("Email notification sent.");
    }

    @GetMapping("/getNotification/{rec}")
    public List<NotificationDTO> getEmailNotification(@PathVariable("rec") String recipient){
        return this.notificationService.getMessage(recipient);
    }

}
