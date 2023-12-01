package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.WorkException;
import com.bricknet.workflow.model.Status;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.repository.WorkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkServiceIMPL implements WorkService {

    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createWorkflow(Work work) throws WorkException {
       work.setActive(true);
       work.setStatus(Status.Initial);
        workRepository.save(work);
    }

    @Override
    public List<Work> getAllworkflowforUser(String employeeId) {
        System.out.println(employeeId);
        List<Work>all= workRepository.findAll().stream().filter(work -> work.isVisibility() || work.getEmployeeAssignedBy().equals(employeeId) || work.isActive() || work.getEmployeeAssignedTo().stream().anyMatch(employeeId::equals))
                .collect(Collectors.toList());
        System.out.println(all);
        return all;

    }


    private static List<Task> updateTaskWithNullUserAndCreatorId(List<Task> tasks) {
        System.out.println(tasks);
        List<Task> nullTask=new ArrayList<>();

        System.out.println(nullTask);
        return nullTask;
    }
   public static Task nullTaskBuilder(String employeeId,Task task){
        Task newTask=Task.builder()
                .title(task.getTitle())
                .status(Status.Initial)
                .description(task.getDescription())
                .employeeAssignedBy(employeeId)
                .employeeAssignedTo(null)
                .estimatedStartDate(null)
                .estimatedEndDate(null)
                .discussion(null)
                .docsList(null)
                .subTasks(null)
                .build();
        return newTask;
   }
    @Override
    public String copyWorkflow(String employeeId, String workId) throws WorkException {
        Work work=workRepository.findById(workId).orElseThrow(()->new WorkException("workflow with  id "+workId+"not found "));
        Work work1=Work.builder()
                .title(work.getTitle())
                .employeeAssignedBy(employeeId)
                .status(Status.Initial)
                .active(true)
                .visibility(work.isVisibility())
                .tasks(work.getTasks())
                .tags(work.getTags())
                .description(work.getDescription())
                .docsList(work.getDocsList())
                .discussion(work.getDiscussion())
                .build();
        System.out.println(work1);
        workRepository.save(work1);
        return "copied please assign people";
    }

    @Override
    public String updateWork(String employeeAssignedBy,Work work) throws WorkException {
        Work work1 = workRepository.findById(work.getWorkId()).orElseThrow(()->new WorkException("This work entity not found"));
        if(work1.getEmployeeAssignedBy().equals(employeeAssignedBy))
        work1.setTitle(work.getTitle());
        work1.setVisibility(work.isVisibility());
        work1.setEmployeeAssignedTo(work.getEmployeeAssignedTo());
        work1.setDescription(work.getDescription());
        work1.setStatus(work.getStatus());
        workRepository.save(work1);
        return "updated";
    }

    @Override
    public String softDeleteWork(String workId) {
        Work softDeleteWork = workRepository.findById(workId).orElse(null);
        softDeleteWork.setActive(false);
        return "Workflow is moved to Bin.";
    }

    @Override
    public String deleteWork(String workId) {
        Work deleteWork = workRepository.findById(workId).orElse(null);
        workRepository.delete(deleteWork);
        return "work deleted";
    }
}
