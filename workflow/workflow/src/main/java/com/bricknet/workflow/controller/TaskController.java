package com.bricknet.workflow.controller;

import com.bricknet.workflow.Exception.TaskException;
import com.bricknet.workflow.dto.TaskDto;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.service.TaskServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private TaskServiceIMPL taskServiceIMPL;

    @PostMapping("/AddTaskInFlow")
    public ResponseEntity<String> AddTaskInFlow(@RequestParam Long userID,@RequestParam Long workID,@RequestBody Task task) throws TaskException {
        String success = taskServiceIMPL.AddTaskInFlow(userID, workID, task);
        return   ResponseEntity.ok(success);
    }
    @PostMapping("/AddTaskInSubTask")
    public ResponseEntity<String> AddTaskInSubTask(@RequestParam Long userID,@RequestParam Long taskId,@RequestBody TaskDto taskdto) throws TaskException {
        Task newtask=Task.builder()
                .creatorId(userID)
                .title(taskdto.getTitle())
                .description(taskdto.getDescription())
                .estimatedStartDate(taskdto.getEstimatedStartDate())
                .estimatedEndDate(taskdto.getEstimatedEndDate())
                .status(taskdto.getStatus())
                .userAssigned(taskdto.getUserAssigned())
                .build();
        String success = taskServiceIMPL.AddTaskInSubTask(userID, taskId, newtask);
        return   ResponseEntity.ok(success);
    }
    @PostMapping("/updateTask")
    public ResponseEntity<String> updateTask(@RequestBody Task task) throws TaskException {
        String success = taskServiceIMPL.updateTask(task);
        return   ResponseEntity.ok(success);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestBody Task task) throws TaskException {
        String success = taskServiceIMPL.deleteTask(task);
        return   ResponseEntity.ok(success);
    }
}
