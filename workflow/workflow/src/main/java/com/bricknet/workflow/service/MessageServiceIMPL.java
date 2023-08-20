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

    @Value("${uploadDirectory}")
    public String uploadDirectory;
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public String workFlowDiscussionMedia(Long workId, MultipartFile file, String userName, String profilePic) throws MessageException {
        Work existingWork = workRepository.findById(workId).orElseThrow(() -> new MessageException("Work not found with ID: " + workId));
        try {
            // Get the original filename of the uploaded file
            String originalFilename = file.getOriginalFilename();

            // Save the uploaded file to the upload directory
            Path path = Paths.get(uploadDirectory + originalFilename);
            Files.write(path, file.getBytes());
            Message newMessage = Message.builder()
                    .userName(userName)
                    .userPicName(profilePic)
                    .content(originalFilename)
                    .timeStamp(LocalDateTime.now())
                    .build();
            List<Message> lastDiscussion = existingWork.getDiscussion();
            lastDiscussion.add(newMessage);
            existingWork.setDiscussion(lastDiscussion);
            workRepository.save(existingWork);
            return "File uploaded successfully: " + originalFilename;
        } catch (IOException e) {
            // Return an error response if the file upload fails
            return "Error uploading file: " + e.getMessage();
        }
    }

    @Override
    public String workFlowDiscussionText(Long workId, String message, String userName, String profilePic) throws MessageException {
        Work existingWork = workRepository.findById(workId).orElseThrow(() -> new MessageException("Work not found with ID: " + workId));
        Message newMessage = Message.builder()
                .userName(userName)
                .userPicName(profilePic)
                .content(message)
                .timeStamp(LocalDateTime.now())
                .build();
        List<Message> lastDiscussion = existingWork.getDiscussion();
        lastDiscussion.add(newMessage);
        existingWork.setDiscussion(lastDiscussion);
        workRepository.save(existingWork);
        return "success";
    }

    @Override
    public String taskDiscussionMedia(Long taskId, MultipartFile file, String userName, String profilePic) throws MessageException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new MessageException("Task not found with ID: " + taskId));
        try {
            // Get the original filename of the uploaded file
            String originalFilename = file.getOriginalFilename();

            // Save the uploaded file to the upload directory
            Path path = Paths.get(uploadDirectory + originalFilename);
            Files.write(path, file.getBytes());
            Message newMessage = Message.builder()
                    .userName(userName)
                    .userPicName(profilePic)
                    .content(originalFilename)
                    .timeStamp(LocalDateTime.now())
                    .build();
            List<Message> lastDiscussion = existingTask.getDiscussion();
            lastDiscussion.add(newMessage);
            existingTask.setDiscussion(lastDiscussion);
            taskRepository.save(existingTask);
            return "File uploaded successfully: " + originalFilename;
        } catch (IOException e) {
            // Return an error response if the file upload fails
            return "Error uploading file: " + e.getMessage();
        }
    }

    @Override
    public String taskDiscussionText(Long taskId, String message, String userName, String profilePic) throws MessageException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new MessageException("Task not found with ID: " + taskId));
        Message newMessage = Message.builder()
                .userName(userName)
                .userPicName(profilePic)
                .content(message)
                .timeStamp(LocalDateTime.now())
                .build();
        List<Message> lastDiscussion = existingTask.getDiscussion();
        lastDiscussion.add(newMessage);
        existingTask.setDiscussion(lastDiscussion);
        taskRepository.save(existingTask);
        return "success";
    }
}
