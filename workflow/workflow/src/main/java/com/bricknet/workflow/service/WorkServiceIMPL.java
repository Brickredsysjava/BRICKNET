package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.WorkException;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceIMPL implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Override
    public void createWorkflow(Work work) throws WorkException {

        workRepository.save(work);
    }

    @Override
    public List<Work> getAllworkflowforUser(Long userId) {
        return workRepository.findAll().stream().filter(work -> work.isVisibility() == true || work.getCreatorId().equals(userId) || work.isActive() == true || work.getUserAssignedTeam().stream().anyMatch(userId::equals))
                .collect(Collectors.toList());

    }


    private static void updateTaskWithNullUserAndCreatorId(List<Task> tasks) {
        tasks.forEach(task -> {
            task.setCreatorId(null);
            task.setUserAssigned(null);
            task.setDiscussion(null);
            task.setDocsList(null);
            updateTaskWithNullUserAndCreatorId((List<Task>) task.getSubTasks());
        });
    }

    @Override
    public String copyWorkflow(Long userId, Work work) {
        work.setCreatorId(userId);
        work.setUserAssignedTeam(null);
        updateTaskWithNullUserAndCreatorId((List<Task>) work.getTasks());
        workRepository.save(work);
        return "copied please assign people";
    }

    @Override
    public String updateWork(Work work) {
        Work work1 = workRepository.findById(work.getWorkId()).orElse(null);
        work1.setTitle(work.getTitle());
        work1.setVisibility(work.isVisibility());
        work1.setUserAssignedTeam(work.getUserAssignedTeam());
        work1.setDescription(work.getDescription());
        work1.setStatus(work.getStatus());
        workRepository.save(work1);
        return "updated";
    }

    @Override
    public String softDeleteWork(Long workId) {
        Work softDeleteWork = workRepository.findById(workId).orElse(null);
        softDeleteWork.setActive(false);
        return "Workflow is moved to Bin.";
    }

    @Override
    public String deleteWork(Long workId) {
        Work deleteWork = workRepository.findById(workId).orElse(null);
        workRepository.delete(deleteWork);
        return "work deleted";
    }
}
