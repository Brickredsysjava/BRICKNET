package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.WorkException;
import com.bricknet.workflow.model.Work;

import java.util.List;

public interface WorkService {
    void createWorkflow(Work work) throws WorkException;

    List<Work> getAllworkflowforUser(String employeeId) throws WorkException;

    String copyWorkflow(String employeeId, String workId) throws WorkException;

    String updateWork(String employeeId,Work work) throws WorkException;

    String softDeleteWork(String workId) throws WorkException;

    String deleteWork(String workId) throws WorkException;
}
