package com.bricknet.communityservice.service;

import com.bricknet.communityservice.dto.CommunityPostDto;
import com.bricknet.communityservice.exception.CommunityException;
import com.bricknet.communityservice.model.CommunityPost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CommunityService {

    public void addPostText(CommunityPostDto communityPostDto) throws CommunityException;
    public String addPostMedia(MultipartFile file) throws CommunityException;

    public List<CommunityPost> getAllPosts() throws CommunityException;

//    public String uploadImage(MultipartFile file) throws IOException;
//    public byte[] downloadImage(String filename);
}
