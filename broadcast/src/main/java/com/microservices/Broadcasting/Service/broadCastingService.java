package com.microservices.Broadcasting.Service;

import com.microservices.Broadcasting.Dto.BroadCastingDTO;
import com.microservices.Broadcasting.Dto.NotificationDTO;
import com.microservices.Broadcasting.Entity.broadCasting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface broadCastingService {

    public broadCasting insertDataIntoDb(broadCasting broadCasting1);

    //public void sendMail(broadCasting broadCasting1) throws MessagingException, IOException;

    public void pushNotification(NotificationDTO notificationDTO);

    public void broadCastingToEveryone(BroadCastingDTO broadCastingDTO);

//    public User createUser(User user);

    public List<BroadCastingDTO> getNewsLetter();

}
