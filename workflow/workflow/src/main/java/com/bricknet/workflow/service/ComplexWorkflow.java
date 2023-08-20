package com.bricknet.workflow.service;

import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;

public interface ComplexWorkflow {
    Work AddWorkflowAsTaskInWork(Long workId, Long userId, Long CurrentWorkId);

    Task AddWorkflowAsSubTaskInTask(Long workId, Long userId, Long CurrentTaskId);
}
