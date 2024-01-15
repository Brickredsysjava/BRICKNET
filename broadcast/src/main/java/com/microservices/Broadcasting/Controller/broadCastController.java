package com.microservices.Broadcasting.Controller;

import com.microservices.Broadcasting.Dto.BroadCastingDTO;
import com.microservices.Broadcasting.Dto.GetBroadcastInfoDTO;
import com.microservices.Broadcasting.Entity.broadCasting;

import com.microservices.Broadcasting.Repository.CustomQuery;
import com.microservices.Broadcasting.Service.GetBroadcastinginfo;
import com.microservices.Broadcasting.Service.broadCastingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/broadcasting")
public class broadCastController {

    @Autowired
    private broadCastingService broadCastingService1;

    private CustomQuery customQuery;

    @Autowired
    private GetBroadcastinginfo getBroadcastinginfo;
    @Value("${upload.path}")
    private String uploadPath;

    public broadCastController(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertDataIntoDb(@RequestBody broadCasting broadCasting1){
    try {
        broadCasting broadCastingSaved = new broadCasting();
        //NotificationDTO notificationDTO = new NotificationDTO();
        BroadCastingDTO broadCastingDTO = new BroadCastingDTO();

        broadCastingSaved.setTitle(broadCasting1.getTitle());
        broadCastingSaved.setEmail(broadCasting1.getEmail());
        broadCastingSaved.setMessage(broadCasting1.getMessage());
        broadCastingSaved.setSelectedDate(broadCasting1.getSelectedDate());
        broadCastingSaved.setStartTime(broadCasting1.getStartTime());
        broadCastingSaved.setEndTime(broadCasting1.getEndTime());
        broadCastingSaved.setTypeOfEvent(broadCasting1.getTypeOfEvent());
        broadCastingSaved.setFileName(broadCasting1.getFileName());
        broadCastingService1.insertDataIntoDb(broadCastingSaved);
        //String fileName = uploadedFile.getOriginalFilename();
        //broadCastingService1.sendMail(broadCastingSaved);

        broadCastingDTO.setBcc(broadCasting1.getEmail());
        broadCastingDTO.setText(broadCasting1.getMessage());
        broadCastingDTO.setSetSubject(broadCasting1.getTitle());
        broadCastingService1.broadCastingToEveryone(broadCastingDTO);

        //notificationDTO.setRecipient(email);
        //notificationDTO.setMessage(message);
        //notificationDTO.setTimestamp(LocalDateTime.now());
        //broadCastingService1.pushNotification(notificationDTO);
        //This is for uploading the file at particular location
       // String filePath = uploadPath + File.separator + fileName;
        //uploadedFile.transferTo(new File(filePath));
        return new ResponseEntity<>("BroadCast created successfully", HttpStatus.CREATED);
    }
    catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
    }
    }

    @GetMapping("/getMailId")
    public ResponseEntity<List<String>> gettheemailIdfromDb(){
        List<String> listOfEmailId = new ArrayList<>();
        listOfEmailId.add("piyushraiangry256@gmail.com");
        listOfEmailId.add("karl98perfect@gmail.com");
        listOfEmailId.add("tubbu32@gmail.com");
        listOfEmailId.add("piyushrai558@gmail.com");
        return new ResponseEntity<>(listOfEmailId, HttpStatus.OK);
    }

    @PostMapping("/upload_file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            // Save the file to the specified upload path
            String fileName = file.getOriginalFilename();
            String filePath = uploadPath + File.separator + fileName;
            file.transferTo(new File(filePath));
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

//    @PostMapping("/create_user")
//    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
//        return new ResponseEntity<>(this.broadCastingService1.createUser(user), HttpStatus.CREATED);
//    }

    @GetMapping("/getFromNotification")
    public String getFromNotification(){
        return this.getBroadcastinginfo.getBroadcastinginfoMethod();
    }

    @GetMapping("/test")
    public String getTest(){
        return "This is broadcast test";
    }

    @GetMapping("/getAllBroadcast")
    public ResponseEntity<?> getAllBroadCast(){
        List<GetBroadcastInfoDTO> list = customQuery.getAllBroadCast();
        if(list!=null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Not Found",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/getNewsLetter")
    public ResponseEntity<?> getNewsLetter(){
        List<GetBroadcastInfoDTO> broadcastingList = broadCastingService1.getNewsLetter();
        if(broadcastingList != null){
            return new ResponseEntity<>(broadcastingList, HttpStatus.OK);
        }
        return new ResponseEntity<>("No news letter found", HttpStatus.NOT_FOUND);
    }

}
