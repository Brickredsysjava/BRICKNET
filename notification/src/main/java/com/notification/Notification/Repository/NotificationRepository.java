package com.notification.Notification.Repository;

import com.notification.Notification.Dto.NotificationDTO;
import com.notification.Notification.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT new com.notification.Notification.Dto.NotificationDTO(n.message, n.timestamp) FROM Notification n WHERE n.recipient = :recipient")
    List<NotificationDTO> findMessageByRecipient(String recipient);
}
