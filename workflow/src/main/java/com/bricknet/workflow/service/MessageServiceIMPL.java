package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.MessageException;
import com.bricknet.workflow.model.Message;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.repository.MessageRepository;
import com.bricknet.workflow.repository.TaskRepository;
import com.bricknet.workflow.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceIMPL implements MessageService {


    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private MessageRepository messageRepository;


//    @Override
//    public String workFlowDiscussionMedia(String workId, MultipartFile file, String userId) throws MessageException, IOException {
//        Work existingWork=workRepository.findById(workId).orElseThrow(()->new MessageException("The workflow you want to add don't exist."));
//        Message newMessage=Message.builder()
//                .userId(userId)
//                .content(Base64Converter.convertToBase64(file.getBytes()))
//                .timeStamp(LocalDateTime.now())
//                .contentType(FileTypeIdentifier.identifyFileType(file.getOriginalFilename())).build();
//        existingWork.getDiscussion().add(newMessage);
//        workRepository.save(existingWork);
//        return  "Message added";
//    }

//    @Override
//    public String workFlowDiscussionText(String workId, String message, String userId) throws MessageException {
//        Work existingWork=workRepository.findById(workId).orElseThrow(()->new MessageException("The workflow you want to add don't exist."));
//        Message newMessage=Message.builder()
//                .userId(userId)
//                .content(message)
//                .timeStamp(LocalDateTime.now())
//                .contentType("text").build();
//        existingWork.getDiscussion().add(newMessage);
//        workRepository.save(existingWork);
//        return  "Message added";
//    }

//    @Override
//    public String taskDiscussionMedia(String taskId, MultipartFile file, String userId) throws MessageException, IOException {
//        Task existingTask=taskRepository.findById(taskId).orElseThrow(()->new MessageException("The task you looking Don't exist."));
//        Message newMessage=Message.builder()
//                .userId(userId)
//                .content(Base64Converter.convertToBase64(file.getBytes()))
//                .timeStamp(LocalDateTime.now())
//                .contentType(FileTypeIdentifier.identifyFileType(file.getOriginalFilename())).build();
//       existingTask.getDiscussion().add(newMessage);
//       taskRepository.save(existingTask);
//        return "Message added";
//    }
//
//    @Override
//    public String taskDiscussionText(String taskId, String message, String userId) throws MessageException {
//        Task existingTask=taskRepository.findById(taskId).orElseThrow(()->new MessageException("The task you looking Don't exist."));
//        Message newMessage=Message.builder()
//                .userId(userId)
//                .content(message)
//                .timeStamp(LocalDateTime.now())
//                .contentType("text").build();
//        existingTask.getDiscussion().add(newMessage);
//        taskRepository.save(existingTask);
//        return "Message added";
//    }
}
