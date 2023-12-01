package com.bricknet.workflow.service;

import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.model.Work;

public interface ComplexWorkflow {
    Work AddWorkflowAsTaskInWork(String workId, String employeeCode, String CurrentWorkId);

    Task AddWorkflowAsSubTaskInTask(String workId, String employeeCode, String CurrentTaskId);
}
