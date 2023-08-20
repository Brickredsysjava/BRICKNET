package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.DocsException;
import com.bricknet.workflow.model.Docs;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.repository.DocsRepository;
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
public class DocsServiceIMPL implements DocsService {

    @Value("${uploadDirectory}")
    public String uploadDirectory;
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private DocsRepository docsRepository;


    @Override
    public String AddDocsToWorkflow(String UserName, String UserPicName, Long workId, MultipartFile file) throws DocsException {

        try {

            // Get the original filename of the uploaded file
            String originalFilename = file.getOriginalFilename();
            FileTypeIdentifier identifier = new FileTypeIdentifier();
            // Save the uploaded file to the upload directory
            Path path = Paths.get(uploadDirectory + originalFilename);
            Files.write(path, file.getBytes());
            Docs newDocs = Docs.builder()
                    .docsName(originalFilename)
                    .timeStamp(LocalDateTime.now())
                    .userPicName(UserPicName)
                    .type(identifier.identifyFileType(originalFilename))
                    .userName(UserName)
                    .build();
            Work existingWork = workRepository.findById(workId).
                    orElseThrow(() -> new DocsException("Task not found with ID: " + workId));
            List<Docs> docsList = existingWork.getDocsList();
            docsList.add(newDocs);
            workRepository.save(existingWork);
            // Return a success response
            return "File uploaded successfully: " + originalFilename;
        } catch (IOException e) {
            // Return an error response if the file upload fails
            return "Error uploading file: " + e.getMessage();
        }

    }

    @Override
    public String AddDocsToTask(String UserName, String UserPicName, Long taskId, MultipartFile file) throws DocsException {
        try {
            // Get the original filename of the uploaded file
            String originalFilename = file.getOriginalFilename();

            // Save the uploaded file to the upload directory
            Path path = Paths.get(uploadDirectory + originalFilename);
            Files.write(path, file.getBytes());
            Docs newDocs = Docs.builder()
                    .docsName(originalFilename)
                    .timeStamp(LocalDateTime.now())
                    .userPicName(UserPicName)
                    .userName(UserName)
                    .build();
            Task existingTask = taskRepository.findById(taskId).
                    orElseThrow(() -> new DocsException("Task not found with ID: " + taskId));
            List<Docs> docsList = existingTask.getDocsList();
            docsList.add(newDocs);
            // Return a success response
            return "File uploaded successfully: " + originalFilename;
        } catch (IOException e) {
            // Return an error response if the file upload fails
            return "Error uploading file: " + e.getMessage();
        }
    }
}
