package org.example.service;

import org.example.dto.*;
import org.example.exception.CommunityException;
import org.example.model.Community;

import javax.management.ServiceNotFoundException;
import java.util.List;

public interface CommunityService {

    public void addPost(Community community) throws CommunityException, ServiceNotFoundException;

    public List<CommunityGetDto> getAllPosts() throws CommunityException;
    public List<CommunityGetDto> getAllPostNeedToVerified() throws CommunityException;

    String addlike(CommunityAddLikeDto communityAddLikeDto) throws CommunityException;

    String postVerification(AdminPostVerificationDto adminPostVerificationDto) throws CommunityException, ServiceNotFoundException;
   String deletePost(CommunityPostDeleteDto communityPostDeleteDto) throws CommunityException;
    String updatePost(CommunityUpdateDto communityUpdateDto) throws CommunityException, ServiceNotFoundException;

  // String updateMedia(String postId, List<MultipartFile> files, String employeeCode, String title, String description) throws CommunityException, IOException, ServiceNotFoundException;

    public List<CommunityGetDto> getMyPost(String employeeCode) throws CommunityException;

    public void pushNotification(NotificationDto notificationDto) throws ServiceNotFoundException;

    String getEmailIdByUserName(String username);
}
