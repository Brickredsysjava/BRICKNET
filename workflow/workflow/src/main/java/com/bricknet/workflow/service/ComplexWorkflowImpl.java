package com.bricknet.workflow.service;

import com.bricknet.workflow.model.Status;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.repository.TaskRepository;
import com.bricknet.workflow.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplexWorkflowImpl implements ComplexWorkflow {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private TaskRepository taskRepository;

    public static List<Task> copySubtasks(List<Task> subTask) {
        List<Task> copiedTask = new ArrayList<>();
        for (Task task : subTask) {
            Task newTask = new Task();
            newTask.setTitle(task.getTitle());
            newTask.setDescription(task.getDescription());
            List<Task> copiedSubTask;
            if (task.getSubTasks() == null) {
                newTask.setSubTasks(null);
            } else if (task.getSubTasks() != null) {
                copiedSubTask = copySubtasks(task.getSubTasks());
                newTask.setSubTasks(copiedSubTask);
            }
            copiedTask.add(newTask);
        }
        return copiedTask;
    }

    @Override
    public Work AddWorkflowAsTaskInWork(Long workId, Long userId, Long currentWorkId) {
        Work currentWork = workRepository.findById(currentWorkId).orElse(null);
        List<Task> previousTask = currentWork.getTasks();
        Work workAsTask = workRepository.findById(workId).orElse(null);
        Task task = new Task();
        task.setTitle(workAsTask.getTitle());
        task.setDescription(workAsTask.getDescription());
        task.setStatus(Status.Initial);
        task.setCreatorId(userId);
        List<Task> SubTask = workAsTask.getTasks();
        task.setSubTasks(copySubtasks(SubTask));
        previousTask.add(task);
        currentWork.setTasks(previousTask);
        workRepository.save(currentWork);
        return currentWork;
    }

    @Override
    public Task AddWorkflowAsSubTaskInTask(Long workId, Long userId, Long currentTaskId) {
        Task currentTask = taskRepository.findById(currentTaskId).orElse(null);
        List<Task> previousTask = currentTask.getSubTasks();
        Work workAsTask = workRepository.findById(workId).orElse(null);
        Task task = new Task();
        task.setTitle(workAsTask.getTitle());
        task.setDescription(workAsTask.getDescription());
        task.setStatus(Status.Initial);
        task.setCreatorId(userId);
        List<Task> SubTask = workAsTask.getTasks();
        task.setSubTasks(copySubtasks(SubTask));
        previousTask.add(task);
        currentTask.setSubTasks(previousTask);
        taskRepository.save(currentTask);
        return currentTask;
    }
}
