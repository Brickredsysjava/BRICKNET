package com.bricknet.workflow.controller;

import com.bricknet.workflow.dto.NotificationDto;
import com.bricknet.workflow.service.ComplexWorkflowImpl;
import com.bricknet.workflow.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/WorkFlow")
@CrossOrigin("*")
public class ComplexWorkflowController {
    @Autowired
    private ComplexWorkflowImpl complexWorkflow;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/AddWorkflowAsTaskInWork")
    public ResponseEntity<String> AddWorkflowAsTaskInWork(Long workId, Long userId, Long currentWorkId) {
        complexWorkflow.AddWorkflowAsTaskInWork(workId, userId, currentWorkId);
        return   ResponseEntity.ok("created");
    }
    @PostMapping("/AddWorkflowAsSubTaskInTask")
    public ResponseEntity<String>AddWorkflowAsSubTaskInTask(Long workId, Long userId, Long currentTaskId) {
        complexWorkflow.AddWorkflowAsSubTaskInTask(workId, userId, currentTaskId);
        return   ResponseEntity.ok("created");
    }

    @GetMapping("/getMessage")
    public ResponseEntity<Mono<NotificationDto>> getMessage(){

        Mono<NotificationDto> responseEntityMono =  this.notificationService.fetchDataFromApi();

        return new ResponseEntity<Mono<NotificationDto>>(responseEntityMono, HttpStatus.OK);
    }
}
