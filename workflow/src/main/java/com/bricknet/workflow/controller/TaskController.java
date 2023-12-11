package com.bricknet.workflow.controller;

import com.bricknet.workflow.Exception.TaskException;
import com.bricknet.workflow.dto.TaskDto;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.service.TaskServiceIMPL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stories/task")
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private TaskServiceIMPL taskServiceIMPL;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/AddTaskInFlow")
    public ResponseEntity<String> AddTaskInFlow(@RequestParam String employeeId,@RequestParam String workID,@RequestBody TaskDto taskdto) throws TaskException {
        Task task=modelMapper.map(taskdto,Task.class);
        task.setEmployeeAssignedBy(employeeId);
        String success = taskServiceIMPL.AddTaskInFlow(employeeId, workID, task);
        return   ResponseEntity.ok(success);
    }
    @PostMapping("/AddTaskInSubTask")
    public ResponseEntity<String> AddTaskInSubTask(@RequestParam String employeeId,@RequestParam String taskId,@RequestBody TaskDto taskdto) throws TaskException {
        Task newtask=modelMapper.map(taskdto,Task.class);
        String success = taskServiceIMPL.AddTaskInSubTask(employeeId, taskId, newtask);
        return   ResponseEntity.ok(success);
    }
    @PostMapping("/updateTask")
    public ResponseEntity<String> updateTask(@RequestParam String employeeId,@RequestBody TaskDto taskDto) throws TaskException {
        Task task=modelMapper.map(taskDto,Task.class);
        String success = taskServiceIMPL.updateTask(employeeId,task);
        return   ResponseEntity.ok(success);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<String> deleteTask(@RequestParam String employeeId,@RequestBody TaskDto taskDto) throws TaskException {
        Task task=modelMapper.map(taskDto,Task.class);
        String success = taskServiceIMPL.deleteTask(employeeId,task);
        return   ResponseEntity.ok(success);
    }
}
