package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.NotificationDTO;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;

@Service
public interface NotificationService {
    public void pushNotification(NotificationDTO notificationDTO) throws ServiceNotFoundException;

}
