package com.bricknet.workflow.controller;

import com.bricknet.workflow.Exception.MessageException;
import com.bricknet.workflow.service.MessageServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/discussion")
@CrossOrigin("*")
public class MessageController {
    @Autowired
    private MessageServiceIMPL messageServiceIMPL;
    @Value("${uploadDirectory}")
    public    String uploadDirectory;
    @PostMapping("/workFlowDiscussionMedia")
    public ResponseEntity<String> workFlowDiscussionMedia(@RequestParam  Long workId, @RequestBody MultipartFile file,@RequestParam String userName,@RequestParam String profilePic) throws MessageException {
     messageServiceIMPL.workFlowDiscussionMedia(workId, file, userName, profilePic);
        return   ResponseEntity.ok("created");
    }
    @PostMapping("/workFlowDiscussionText")
    public ResponseEntity<String> workFlowDiscussionText(@RequestParam  Long workId, @RequestBody String message,@RequestParam String userName,@RequestParam String profilePic) throws MessageException {
        messageServiceIMPL.workFlowDiscussionText(workId, message, userName, profilePic);
        return   ResponseEntity.ok("created");
    }
    @PostMapping("/taskDiscussionMedia")
    public ResponseEntity<String> taskDiscussionMedia(@RequestParam Long  taskId, @RequestBody MultipartFile file, @RequestParam String userName, @RequestParam String profilePic) throws MessageException {
        messageServiceIMPL.taskDiscussionMedia(taskId, file, userName, profilePic);
        return   ResponseEntity.ok("created");
    }
    @PostMapping("/taskDiscussionText")
    public ResponseEntity<String> taskDiscussionText(@RequestBody  Long taskId, @RequestBody String message,@RequestParam String userName,@RequestParam String profilePic) throws MessageException {
        messageServiceIMPL.taskDiscussionText(taskId,message, userName, profilePic);
        return   ResponseEntity.ok("created");
    }
    @PostMapping("/addmediademo")
    public String addmediademo(@RequestBody  MultipartFile file) {
        try {
            // Get the original filename of the uploaded file
            String originalFilename = file.getOriginalFilename();

            // Save the uploaded file to the upload directory
            Path path = Paths.get(uploadDirectory + originalFilename);
            Files.write(path,file.getBytes());


            System.out.println("out IMPL");
        } catch (IOException e) {
            // Return an error response if the file upload fails
            return "Error uploading file: " + e.getMessage();
        }
        return null;
    }
}
