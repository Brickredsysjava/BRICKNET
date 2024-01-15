package com.notification.Notification.Controller;

import com.notification.Notification.Dto.BroadCastingDTO;
import com.notification.Notification.Entity.Notification;
import com.notification.Notification.Repository.CustomQuery;
import com.notification.Notification.Service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/send")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    private CustomQuery customQuery;

    public NotificationController(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmailNotification(
            @Valid @RequestBody Notification notification) {
        String recipient = notification.getRecipient();
        String message = notification.getMessage();
        notificationService.sendEmailNotification(recipient, message);
        return ResponseEntity.ok("Email notification sent.");
    }

    @GetMapping("/getNotification")
    public ResponseEntity<List<String>> getEmailNotification(@RequestParam("employeeCode") String employeeCode){
        List<String> result = customQuery.getAllNotificationByEmployeeCode(employeeCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
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
