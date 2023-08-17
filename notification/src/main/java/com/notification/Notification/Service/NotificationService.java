package com.notification.Notification.Service;

import com.notification.Notification.Dto.NotificationDTO;
import com.notification.Notification.Entity.Notification;

import java.util.List;
import java.util.Objects;

public interface NotificationService {

    public void sendEmailNotification(String recipient, String message);

    public void  sendEmail(String to, String subject, String text);

    public List<NotificationDTO> getMessage(String recipient);
}
