package com.notification.Notification.Controller;

import com.notification.Notification.Dto.BroadCastingDTO;
import com.notification.Notification.Dto.NotificationDTO;
import com.notification.Notification.Entity.Notification;
import com.notification.Notification.Service.NotificationService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/send")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmailNotification(
            @Valid @RequestBody Notification notification) {
        String recipient = notification.getRecipient();
        String message = notification.getMessage();
        notificationService.sendEmailNotification(recipient, message);
        return ResponseEntity.ok("Email notification sent.");
    }

    @GetMapping("/getNotification/{rec}")
    public List<NotificationDTO> getEmailNotification(@PathVariable("rec") String recipient){
        return this.notificationService.getMessage(recipient);
    }

    @GetMapping("/tests")
    public String getTest(){
        return "Notification services are up and running";
    }

    @PostMapping("/broadcast")
    public ResponseEntity<String> broadcastMails(@RequestBody BroadCastingDTO broadCastingDTO){
        notificationService.broadCastMessage(broadCastingDTO.getBcc(), broadCastingDTO.getSetSubject(), broadCastingDTO.getText());
        return new ResponseEntity<>("It is sent", HttpStatus.OK);
    }

}
