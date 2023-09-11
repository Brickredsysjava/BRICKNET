package com.microservices.Broadcasting.Controller;

import com.microservices.Broadcasting.Dto.BroadcastingDto;

import com.microservices.Broadcasting.Eception.BroadcastingException;
import com.microservices.Broadcasting.Entity.BroadCasting;
import com.microservices.Broadcasting.Entity.EventType;
import com.microservices.Broadcasting.Entity.User;

import com.microservices.Broadcasting.Service.broadCastingService;
import com.microservices.Broadcasting.ServiceImpl.FileTypeIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/broadcasting")
public class broadCastController {

    @Autowired
    private broadCastingService broadCastingService;


    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/createBroadcast")
    public ResponseEntity<String> createBroadcast(BroadcastingDto broadcastingDto) throws BroadcastingException{
        broadCastingService.createBroadcast(broadcastingDto);
        return ResponseEntity.ok("Broadcast Created Successfully");
    }


    @GetMapping("/getMailId")
    public ResponseEntity<List<String>> getTheEmailIdFromDb() {
        List<String> listOfEmailId = new ArrayList<>();
        listOfEmailId.add("piyushraiangry256@gmail.com");
        listOfEmailId.add("karl98perfect@gmail.com");
        listOfEmailId.add("tubbu32@gmail.com");
        listOfEmailId.add("piyushrai558@gmail.com");
        listOfEmailId.add("parth21@gmail.com");
        return new ResponseEntity<>(listOfEmailId, HttpStatus.OK);
    }





    @PostMapping("/create_user")
    public ResponseEntity<User> createUser(@RequestBody User user) throws BroadcastingException {
        return new ResponseEntity<>(this.broadCastingService.createUser(user), HttpStatus.CREATED);
    }


//    @PostMapping("/file")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            // Validate and upload the file
//            if (file != null && !file.isEmpty()) {
//                broadCastingService.uploadFile(file);
//                return ResponseEntity.ok("File uploaded successfully.");
//            } else {
//                return ResponseEntity.badRequest().body("Please select a file to upload.");
//            }
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
//        }
//    }




    @GetMapping("/getAllEventType")
    public List<EventType> getAllEventTypes() {
        return broadCastingService.getAllEventType();
    }



//    @GetMapping("/receiver-emails")
//    public List<String> getAllReceiverEmails() {
//        return broadCastingService.getAllReceiverEmails();
//    }


    @PostMapping("/images/upload-api")
    @ResponseBody
    public String handleUpload(@RequestBody MultipartFile file, @RequestParam String title, @RequestParam String description) throws BroadcastingException {


        if (file instanceof MultipartFile == true) {
            try {
                BroadCasting broadCastingFile = BroadCasting.builder()
                        .title(title)
                        .message(description)
                        .startTime(String.valueOf(new Date()))
                        .endTime(String.valueOf(new Date()))
                        .build();

                // Get the original filename of the uploaded file
                String originalFilename = file.getOriginalFilename();
                FileTypeIdentifier identifier = new FileTypeIdentifier();
                broadCastingFile.setContentType(identifier.identifyFileType(originalFilename));
                // Save the uploaded file to the upload directory
                Path path = Paths.get(uploadPath + originalFilename);
                Files.write(path, file.getBytes());
                broadCastingFile.setContent(originalFilename);
                broadCastingService.addFile(broadCastingFile);
                return "File uploaded successfully: " + originalFilename;
            } catch (IOException e) {
                // Return an error response if the file upload fails
                return "Error uploading file: " + e.getMessage();
            }
        } else {

            throw new BroadcastingException("Provided file is not a media type file");
        }
    }





}
