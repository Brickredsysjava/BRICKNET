package org.example.service;

import org.example.dto.CommunityGetDto;
import org.example.dto.CommunityPostDto;
import org.example.dto.CommunityUpdateDto;
import org.example.dto.NotificationDto;
import org.example.exception.CommunityException;
import org.example.model.Community;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.management.ServiceNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CommunityService {

    public void addPostText(Community community) throws CommunityException, ServiceNotFoundException;
    public void addPostMedia(Community community) throws CommunityException, ServiceNotFoundException;

    public List<CommunityGetDto> getAllPosts() throws CommunityException;
    public List<CommunityGetDto> getAllPostNeedToVerified() throws CommunityException;

    String addlike(String postId, String employeeCode, Boolean like) throws CommunityException;

    String postVerification(String postId,Boolean adminVerfied) throws CommunityException;
    String deletePost(String  postId,String employeeCode) throws CommunityException;
    String updatePost(String postId, CommunityUpdateDto communityUpdateDto,String employeeCode) throws CommunityException, ServiceNotFoundException;

   String updateMedia(String postId,List<MultipartFile>  files, String employeeCode,String title, String description) throws CommunityException, IOException, ServiceNotFoundException;

    public List<CommunityGetDto> getMyPost(String employeeCode) throws CommunityException;

    public void pushNotification(NotificationDto notificationDto) throws ServiceNotFoundException;

}
