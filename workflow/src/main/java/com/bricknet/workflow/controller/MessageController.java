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

//    @PostMapping("/workFlowDiscussionMedia")
//    public ResponseEntity<String> workFlowDiscussionMedia(@RequestParam  String workId, @RequestBody MultipartFile file,@RequestParam String userId) throws MessageException, IOException {
//     messageServiceIMPL.workFlowDiscussionMedia(workId, file, userId);
//        return   ResponseEntity.ok("created");
//    }
//    @PostMapping("/workFlowDiscussionText")
//    public ResponseEntity<String> workFlowDiscussionText(@RequestParam  String workId, @RequestBody String message,@RequestParam String userId) throws MessageException {
//        messageServiceIMPL.workFlowDiscussionText(workId, message, userId);
//        return   ResponseEntity.ok("created");
//    }
//    @PostMapping("/taskDiscussionMedia")
//    public ResponseEntity<String> taskDiscussionMedia(@RequestParam String  taskId, @RequestBody MultipartFile file, @RequestParam String userID) throws MessageException, IOException {
//        messageServiceIMPL.taskDiscussionMedia(taskId, file, userID);
//        return   ResponseEntity.ok("created");
//    }
//    @PostMapping("/taskDiscussionText")
//    public ResponseEntity<String> taskDiscussionText(@RequestBody  String taskId, @RequestBody String message,@RequestParam String userId) throws MessageException {
//        messageServiceIMPL.taskDiscussionText(taskId,message, userId);
//        return   ResponseEntity.ok("created");
//    }

}
