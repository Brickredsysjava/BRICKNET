package com.bricknet.workflow.controller;

import com.bricknet.workflow.Exception.DocsException;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.service.DocsServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/docs")
@CrossOrigin("*")
public class DocsController {
    @Autowired
    private DocsServiceIMPL docsServiceIMPL;
    @PostMapping("/AddDocsToWorkflow")
    public ResponseEntity<String> AddDocsToWorkflow(@RequestParam String UserName,@RequestParam  String UserPicName,@RequestParam Long workId, @RequestParam MultipartFile file) throws DocsException {
       docsServiceIMPL.AddDocsToWorkflow(UserName, UserPicName, workId, file);
        return   ResponseEntity.ok("created");
    }
    @PostMapping("/AddDocsToTask")
    public ResponseEntity<String> AddDocsToTask(@RequestParam String UserName, @RequestParam  String UserPicName, @RequestParam Long taskId, @RequestParam MultipartFile file) throws DocsException {
        docsServiceIMPL.AddDocsToTask(UserName, UserPicName, taskId, file);
        return   ResponseEntity.ok("created");
    }

}
