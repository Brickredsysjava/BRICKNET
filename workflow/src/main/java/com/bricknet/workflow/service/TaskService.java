package com.bricknet.workflow.service;


import com.bricknet.workflow.Exception.TaskException;
import com.bricknet.workflow.model.Task;
import org.springframework.web.bind.annotation.RequestParam;

public interface TaskService {
    String AddTaskInFlow(String userID, String workID, Task task) throws TaskException;

    String AddTaskInSubTask(String userID, String taskId, Task task) throws TaskException;

    String updateTask( String employeeId,Task task) throws TaskException;

    String deleteTask(String employeeId,Task task) throws TaskException;
}
