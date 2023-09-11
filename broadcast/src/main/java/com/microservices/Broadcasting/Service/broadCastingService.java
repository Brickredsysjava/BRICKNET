package com.microservices.Broadcasting.Service;

import com.microservices.Broadcasting.Dto.BroadcastingDto;
import com.microservices.Broadcasting.Dto.NotificationDto;
import com.microservices.Broadcasting.Eception.BroadcastingException;
import com.microservices.Broadcasting.Entity.BroadCasting;
import com.microservices.Broadcasting.Entity.EventType;
import com.microservices.Broadcasting.Entity.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface broadCastingService {

    public void createBroadcast(BroadcastingDto broadcastingDto) throws BroadcastingException;

    public void pushNotification(NotificationDto notificationDTO) throws BroadcastingException;

    public User createUser(User user) throws BroadcastingException;

//    public void uploadFile(MultipartFile file) throws IOException,FileNotFoundException;

    public  List<EventType> getAllEventType();

//    public List<String> getAllReceiverEmails();


    public void addFile(BroadCasting broadCasting) throws BroadcastingException;

}
