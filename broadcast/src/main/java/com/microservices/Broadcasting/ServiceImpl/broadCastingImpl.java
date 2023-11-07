package com.microservices.Broadcasting.ServiceImpl;

import com.microservices.Broadcasting.Dto.NotificationDTO;
import com.microservices.Broadcasting.Entity.broadCasting;
import com.microservices.Broadcasting.Repository.broadCastingRepo;
import com.microservices.Broadcasting.Service.broadCastingService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class broadCastingImpl implements broadCastingService{

    private final JavaMailSender mailSender;

    private static final String TOPIC_NAME = "user-events";

    private WebClient.Builder webClientBuilder;

    private KafkaTemplate<String, String> kafkaTemplate;

//    @Autowired
//    private UserCreationRepo userCreationRepo;

    @Autowired
    private broadCastingRepo  broadCastingRepo1;

    public broadCastingImpl(JavaMailSender mailSender, WebClient.Builder webClientBuilder) {
        this.mailSender = mailSender;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public broadCasting insertDataIntoDb(broadCasting broadCasting1) {
        System.out.println("This is the data we are getting ");
        System.out.println(broadCasting1);

        return this.broadCastingRepo1.save(broadCasting1);
    }

    @Override
    public void sendMail(broadCasting broadCasting1) throws MessagingException, IOException {
        SimpleMailMessage message = new SimpleMailMessage();

       // Multipart multipart = new MimeMultipart();

        //MimeBodyPart attachmentPart = new MimeBodyPart();

        //String attachmentFilePath = "C:/Users/piyush/Desktop/uploadedfile/" + name;

        //attachmentPart.attachFile(attachmentFilePath);


        message.setTo(broadCasting1.getEmail());
        message.setSubject(broadCasting1.getTitle());
        message.setCc(broadCasting1.getSelectedOption());

        //message.setText(broadCasting1.getMessage());
        //message.setSentDate(broadCasting1.getSelectedDate());
        message.setText( "Hello there " + ",\n"
                + "\n"
        + broadCasting1.getMessage()+ "\n" + " This event would be held on " + broadCasting1.getSelectedDate()
        + " Date \n"+
                "And " + broadCasting1.getTime() + " is the time of event. \n"
                + "\n"
                + "Thanks & Regards\n,"
                + "Hr@BrickredsysIndia.com");
        mailSender.send(message);
    }

    @Override
    public void pushNotification(NotificationDTO notificationDTO) {
        String jsonBody = "{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.1.9:8084/send/")
                .build().post().uri("/email").bodyValue(notificationDTO).retrieve().
                toBodilessEntity().block();
    }

//    @Override
//    public User createUser(User user) {
//        String message = "User Created " + user.getId() + user.getName();
//        kafkaTemplate.send("User Created", message);
//        return this.userCreationRepo.save(user);
//    }


}
