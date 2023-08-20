package com.notification.Notification.ServiceImpl;

import com.notification.Notification.Dto.NotificationDTO;
import com.notification.Notification.Entity.Notification;
import com.notification.Notification.Repository.NotificationRepository;
import com.notification.Notification.Service.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendEmailNotification(String recipient, String message) {

        Notification notification = new Notification();

        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setRecipient(recipient);

        String mailMessage = "Hi there, \n" +
                "you've new notification " + message + " \n"
                + "Thanks & Regards \n" +
                String.valueOf(mailSender);


        //sending mail
        sendEmail(recipient, "New Notification", mailMessage);

        notificationRepository.save(notification);
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public List<NotificationDTO> getMessage(String recipient) {
        return this.notificationRepository.findMessageByRecipient(recipient);
    }
}
