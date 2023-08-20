package com.bricknet.workflow.service;


import com.bricknet.workflow.Exception.TaskException;
import com.bricknet.workflow.model.Task;

public interface TaskService {
    String AddTaskInFlow(Long userID, Long workID, Task task) throws TaskException;

    String AddTaskInSubTask(Long userID, Long taskId, Task task) throws TaskException;

    String updateTask(Task task) throws TaskException;

    String deleteTask(Task task) throws TaskException;
}
