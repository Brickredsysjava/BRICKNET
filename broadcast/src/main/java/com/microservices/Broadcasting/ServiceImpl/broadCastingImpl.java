package com.microservices.Broadcasting.ServiceImpl;

import com.microservices.Broadcasting.Dto.BroadcastingDto;
import com.microservices.Broadcasting.Dto.NotificationDto;
import com.microservices.Broadcasting.Eception.BroadcastingException;
import com.microservices.Broadcasting.Entity.EventType;
import com.microservices.Broadcasting.Entity.User;
import com.microservices.Broadcasting.Entity.BroadCasting;
import com.microservices.Broadcasting.Repository.BroadCastingRepo;
import com.microservices.Broadcasting.Repository.UserCreationRepo;
import com.microservices.Broadcasting.Service.broadCastingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class broadCastingImpl implements broadCastingService {

    private final JavaMailSender mailSender;

    private static final String TOPIC_NAME = "user-events";

    private WebClient.Builder webClientBuilder;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private BroadCastingRepo broadCastingRepo;

    @Autowired
    private UserCreationRepo userCreationRepo;

    public broadCastingImpl(JavaMailSender mailSender, WebClient.Builder webClientBuilder) {
        this.mailSender = mailSender;
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public void createBroadcast(BroadcastingDto broadcastingDto) throws BroadcastingException {
        if (broadcastingDto != null) {
            BroadCasting broadCasting = BroadCasting.builder()
                    .title(broadcastingDto.getTitle())
                    .receiverEmail(broadcastingDto.getReceiverEmail())
                    .date(broadcastingDto.getDate())
                    .message(broadcastingDto.getMessage())
                    .startTime(broadcastingDto.getStartTime())
                    .endTime(broadcastingDto.getEndTime())
                    .Cc(broadcastingDto.getCc())
                    .eventType(broadcastingDto.getEventType())
                    .build();

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message = "Hello there " + ",\n"
                        + "\n"
                        + broadcastingDto.getTitle() + "\n" + "This Event will be held on \n"
                        + broadcastingDto.getDate() + "    at   " +
                        broadcastingDto.getStartTime() + " - " + broadcastingDto.getEndTime()
                        + "\n"
                        + "Thanks & Regards, \n"
                        + "Hr@BrickredsysIndia.com";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient(broadcastingDto.getReceiverEmail());
                notificationDto.setTimeStamp(localDateTime);

                pushNotification(notificationDto);
            } finally {
                broadCastingRepo.save(broadCasting);
                System.out.println("CONNECTION REFUSED");
            }
        } else {
            throw new BroadcastingException("PLEASE GIVE PROPER INPUTS");
        }
    }


    @Override
    public void pushNotification(NotificationDto notificationDTO) {
        String jsonBody = "{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.1.96:8096/send/")
                .build().post().uri("/email").bodyValue(notificationDTO).retrieve().
                toBodilessEntity().block();
    }


    @Override
    public User createUser(User user) {
        String message = "User Created " + user.getId() + user.getName();
        kafkaTemplate.send("User Created", message);
        return this.userCreationRepo.save(user);
    }


//    public void uploadFile(MultipartFile file) throws IOException {
//        BroadCasting file1 = new BroadCasting();
//        file1.setFileName(file.getOriginalFilename());
//        file1.setFileData(file.getBytes());
//        broadCastingRepo.save(file1);
//    }


    public List<EventType> getAllEventType() {
        return Arrays.stream(EventType.values())
                .collect(Collectors.toList());
    }


    /*
        public List<String> getAllReceiverEmails() {
            return broadCastingRepo.findAll().stream()
                    .map(BroadCasting::getReceiverEmail)
                    .distinct()
                    .toList();
        }
    */
    @Override
    public void addFile(BroadCasting broadCasting) throws BroadcastingException {
        if (broadCasting != null) {

            broadCastingRepo.save(broadCasting);
        } else {
            throw new BroadcastingException("Upload failed");

        }
    }

}