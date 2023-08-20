package com.bricknet.workflow.service;

import com.bricknet.workflow.Exception.MessageException;
import org.springframework.web.multipart.MultipartFile;

public interface MessageService {
    String workFlowDiscussionMedia(Long workId, MultipartFile file, String userName, String profilePic) throws MessageException;

    String workFlowDiscussionText(Long workId, String message, String userName, String profilePic) throws MessageException;

    String taskDiscussionMedia(Long taskId, MultipartFile file, String userName, String profilePic) throws MessageException;

    String taskDiscussionText(Long taskId, String message, String userName, String profilePic) throws MessageException;
}
