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


    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private DocsRepository docsRepository;


//    @Override
//    public String AddDocsToWorkflow(String employeeCode, String workId, MultipartFile file) throws DocsException, IOException {
//
//        Work existingWork=workRepository.findById(workId).orElseThrow(()->new DocsException("The flow you want to upload  don't Exist  .check WorkFlow-ID"+workId));
//        Docs newDoc=Docs.builder()
//                        .employeeCode(employeeCode)
//                                .content(Base64Converter.convertToBase64(file.getBytes()))
//                                        .type(FileTypeIdentifier.identifyFileType(file.getOriginalFilename()))
//                                                .build();
//        existingWork.getDocsList().add(newDoc);
//        workRepository.save(existingWork);
//        return "Added";
//    }

//    @Override
//    public String AddDocsToTask(String userId, String taskId, MultipartFile file) throws DocsException, IOException {
//        Task existingTask=taskRepository.findById(taskId).orElseThrow(()->new DocsException("The task you want to upload  don't Exist  .check WorkFlow-ID"+taskId));
//        Docs newDoc=Docs.builder()
//                .userId(userId)
//                .content(Base64Converter.convertToBase64(file.getBytes()))
//                .type(FileTypeIdentifier.identifyFileType(file.getOriginalFilename()))
//                .build();
//        existingTask.getDocsList().add(newDoc);
//        return "Added";
//    }
}
