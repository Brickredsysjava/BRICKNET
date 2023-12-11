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
@RequestMapping("/stories/workFlow")
@CrossOrigin("*")
public class ComplexWorkflowController {
    @Autowired
    private ComplexWorkflowImpl complexWorkflow;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/AddWorkflowAsTaskInWork")
    public ResponseEntity<String> AddWorkflowAsTaskInWork(@RequestParam   String workId,@RequestParam String employeeId,@RequestParam String currentWorkId) {
        complexWorkflow.AddWorkflowAsTaskInWork(workId, employeeId, currentWorkId);
        return   ResponseEntity.ok("created");
    }
    @PostMapping("/AddWorkflowAsSubTaskInTask")
    public ResponseEntity<String>AddWorkflowAsSubTaskInTask(@RequestParam String workId,@RequestParam String employeeId,@RequestParam String currentTaskId) {
        complexWorkflow.AddWorkflowAsSubTaskInTask(workId, employeeId, currentTaskId);
        return   ResponseEntity.ok("created");
    }

    @GetMapping("/getMessage")
    public ResponseEntity<Mono<NotificationDto>> getMessage(){

        Mono<NotificationDto> responseEntityMono =  this.notificationService.fetchDataFromApi();

        return new ResponseEntity<Mono<NotificationDto>>(responseEntityMono, HttpStatus.OK);
    }
}
