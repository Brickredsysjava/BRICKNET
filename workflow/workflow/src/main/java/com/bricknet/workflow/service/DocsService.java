package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.DocsException;
import org.springframework.web.multipart.MultipartFile;

public interface DocsService {
    String AddDocsToWorkflow(String UserName, String UserPicName, Long workId, MultipartFile file) throws DocsException;

    String AddDocsToTask(String UserName, String UserPicName, Long taskId, MultipartFile file) throws DocsException;
}
