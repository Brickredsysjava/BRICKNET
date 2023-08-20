package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.WorkException;
import com.bricknet.workflow.model.Work;

import java.util.List;

public interface WorkService {
    void createWorkflow(Work work) throws WorkException;

    List<Work> getAllworkflowforUser(Long UserId) throws WorkException;

    String copyWorkflow(Long userId, Work work) throws WorkException;

    String updateWork(Work work) throws WorkException;

    String softDeleteWork(Long workId) throws WorkException;

    String deleteWork(Long workId) throws WorkException;
}
