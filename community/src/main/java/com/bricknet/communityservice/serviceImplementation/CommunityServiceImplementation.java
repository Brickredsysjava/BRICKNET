package com.bricknet.communityservice.serviceImplementation;

import com.bricknet.communityservice.dto.CommunityPostDto;
import com.bricknet.communityservice.exception.CommunityException;
import com.bricknet.communityservice.model.CommunityPost;
import com.bricknet.communityservice.repository.CommunityRepository;
import com.bricknet.communityservice.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CommunityServiceImplementation implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Value("${uploadDirectory}")
    public    String uploadDirectory ;//    private List<CommunityPost> communityPosts;
//
//    public CommunityServiceImplementation() {
//        communityPosts = new ArrayList<CommunityPost>();
//    }
    @Override
    public void addPostText(CommunityPostDto communityPostDto) throws CommunityException {

        if(communityPostDto!=null){
            CommunityPost communityPost = CommunityPost.builder()
                    .employee_name(communityPostDto.getEmployee_name())
                    .title(communityPostDto.getTitle())
                    .date_time(communityPostDto.getDate_time())
                    .description(communityPostDto.getDescription())
                    .profileImage(communityPostDto.getProfileImage())
                    .content(communityPostDto.getContent())
                    .build();

            communityRepository.save(communityPost);
        }else{
            //throw exception
            throw new CommunityException("Please give proper input");
        }
    }

    @Override
    public String addPostMedia(MultipartFile file) throws CommunityException {
        try {
            // Get the original filename of the uploaded file
            String originalFilename = file.getOriginalFilename();

            // Save the uploaded file to the upload directory
            Path path = Paths.get(uploadDirectory + originalFilename);
            Files.write(path, file.getBytes());


            // Return a success response

        } catch (IOException e) {
            // Return an error response if the file upload fails
            return "Error uploading file: " + e.getMessage();
        }
        return null;
    }

    @Override
    public List<CommunityPost> getAllPosts() throws CommunityException {
        return communityRepository.findAll();
    }

//    @Override
//    public String uploadImage(MultipartFile file) throws IOException {
//        CommunityPost communityPost=communityRepository.save(CommunityPost.builder()
//                .profileImage(ImageUtils.compressImage(file.getBytes())).build());
//
//        if(communityPost!=null){
//            return "file uploaded successfully"+file.getOriginalFilename();
//        }
//        return null;
//    }
//
//    @Override
//    public byte[] downloadImage(String filename){
//        Optional<CommunityPost> dbImageData= communityRepository.findImageByName(filename);
//       byte[] images= ImageUtils.decompressImage(dbImageData.get().getProfileImage());
//       return images;
//    }
}

