package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.TaskException;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.repository.TaskRepository;
import com.bricknet.workflow.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceIMPL implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkRepository workRepository;

    @Override
    public String AddTaskInFlow(Long userID, Long workID, Task task) throws TaskException {
        Work existingWork = workRepository.findById(workID).orElseThrow(() -> new TaskException("Work not found with ID: " + workID));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(existingWork);
        existingWork.setCreatorId(userID);
        List<Task> newTaskList = existingWork.getTasks();
        newTaskList.add(task);
        existingWork.setTasks(newTaskList);
        workRepository.save(existingWork);
        return "task added";

    }

    @Override
    public String AddTaskInSubTask(Long userID, Long taskId, Task task) throws TaskException {
        Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskException("ParentTask not found with ID: " + taskId));
        existingTask.setCreatorId(userID);
        List<Task> newSubTaskList = existingTask.getSubTasks();
        newSubTaskList.add(task);
        existingTask.setSubTasks(newSubTaskList);
        taskRepository.save(existingTask);
        return "task added ";
    }

    @Override
    public String updateTask(Task task) throws TaskException {
        Task existingTask = taskRepository.findById(task.getTaskId()).orElseThrow(() -> new TaskException("Task not found with ID: " + task.getTaskId()));
        existingTask.setTitle(task.getTitle());
        existingTask.setUserAssigned(task.getUserAssigned());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        taskRepository.save(existingTask);
        return " Task updated";
    }

    @Override
    public String deleteTask(Task task) throws TaskException {
        if (task != null) {
            taskRepository.delete(task);
            return "task Deleted.";
        } else {
            throw new TaskException("TAsk not found with this record");
        }
    }
}
