package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.dto.CommunityGetDto;
import org.example.dto.CommunityUpdateDto;
import org.example.dto.NotificationDto;
import org.example.exception.CommunityException;
import org.example.model.Community;
import org.example.model.ContentDetails;
import org.example.repository.CommunityRepository;
import org.example.repository.ContentDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.ServiceNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImplementation implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ContentDetailsRepository contentDetailsRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${uploadDirectory}")
    public    String uploadDirectory ;


    @Override
    public void addPostText(Community community) throws CommunityException , ServiceNotFoundException{

        if (community != null) {
            //Notification cod
            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "\nFrom - " + community.getEmployee_code() +
                                "\nTitle - " + community.getTitle() +
                                "\n" + "Click here for more info: \n";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient("titango129@gmail.com");
                notificationDto.setTimestamp(localDateTime);

                pushNotification(notificationDto);
            }
            finally {
                System.out.println("Connection refused");
                communityRepository.save(community);
            }

        } else {
            throw new CommunityException("Details are not exist");

        }
    }

    @Override
    public void addPostMedia(Community community) throws CommunityException, ServiceNotFoundException {
        if (community != null) {

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "\nFrom - " + community.getEmployee_code() +
                                "\nTitle - " + community.getTitle() +
                                "\n" + "Click here for more info: \n";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient("titango129@gmail.com");
                notificationDto.setTimestamp(localDateTime);

                pushNotification(notificationDto);
            }
            finally {
                System.out.println("Connection refused");
                communityRepository.save(community);
            }
//            communityRepository.save(community);
        } else {
            throw new CommunityException("Upload failed");

        }
    }


    @Override
    public List<CommunityGetDto> getAllPosts() throws CommunityException{
        List<CommunityGetDto> newDtoList = new ArrayList<>();
        List<CommunityGetDto> dtoList = communityRepository.findAll().stream().map(p -> {
            // Integer likeCount = p.getLikedEmployee().size();
            CommunityGetDto communityGetDto = null;
            if (p.getAdminVerified() == true) {
                List<String> likedEmployee = p.getLikedEmployee();
                communityGetDto = CommunityGetDto.builder()
                        .Id(p.getPost_id())
                        .title(p.getTitle())
                        .employee_code(p.getEmployee_code())
                        .date_time(p.getDate_time())
                        .likeCount((long) likedEmployee.size())
                        .likedEmployee(p.getLikedEmployee())
                        .description(p.getDescription())
                        .contents(p.getContents())
                        .adminVerified(p.getAdminVerified())
                        .verificationStatusMessage(p.getVerificationStatusMessage())
//                        .adminVerificationStatus(p.getAdminVerificationStatus())
                        .build();
                newDtoList.add(communityGetDto);
            }
            return communityGetDto;


        }).collect(Collectors.toList());

        Collections.sort(newDtoList, Comparator.comparing(CommunityGetDto::getDate_time).reversed());

        return newDtoList;
    }

    @Override
    public List<CommunityGetDto> getAllPostNeedToVerified() throws CommunityException {
        List<CommunityGetDto> newDtoList = new ArrayList<>();
        List<CommunityGetDto> dtoList = communityRepository.findAll().stream().map(p -> {
            // Integer likeCount = p.getLikedEmployee().size();
            CommunityGetDto communityGetDto = null;
            if (Objects.equals(p.getVerificationStatusMessage(), "Pending")) {
                List<String> likedEmployee = p.getLikedEmployee();
                communityGetDto = CommunityGetDto.builder()
                        .Id(p.getPost_id())
                        .title(p.getTitle())
                        .employee_code(p.getEmployee_code())
                        .date_time(p.getDate_time())
                        .likeCount((long) likedEmployee.size())
                        .likedEmployee(p.getLikedEmployee())
                        .description(p.getDescription())
                        .contents(p.getContents())
                        .adminVerified(p.getAdminVerified())
                        .verificationStatusMessage(p.getVerificationStatusMessage())
//                        .adminVerificationStatus(p.getAdminVerificationStatus())
                        .build();
                newDtoList.add(communityGetDto);
            }
            return communityGetDto;


        }).collect(Collectors.toList());

        // Sorting the newDtoList by date_time in descending order
        Collections.sort(newDtoList, Comparator.comparing(CommunityGetDto::getDate_time).reversed());

        return newDtoList;
    }


    @Override
    public String addlike(String postId, String employeeCode, Boolean like) throws CommunityException {
        Community currentPost = communityRepository.findById(postId).orElseThrow(() -> new CommunityException("post not found"));


            List<String> likedEmployee = currentPost.getLikedEmployee();
            if (likedEmployee == null) {
                //initialize with an empty list
                likedEmployee = new ArrayList<>();
            }
            boolean alreadyLiked = likedEmployee.contains(employeeCode);

            if (like && !alreadyLiked) {
                //add a new like
                likedEmployee.add(employeeCode);

            } else if (!like && alreadyLiked) {
                //remove an existing like
                likedEmployee.remove(employeeCode);

            }
            //else do nothing

            currentPost.setLikedEmployee(likedEmployee);
            communityRepository.save(currentPost);
        return "done";
    }

    @Override
    public String postVerification(String postId, Boolean adminVerfied) throws CommunityException {
        Community currentPost = communityRepository.findById(postId).orElseThrow(() -> new CommunityException("post not found"));


        if (adminVerfied) {
            currentPost.setAdminVerified(true);
            currentPost.setVerificationStatusMessage("Approved");
//            currentPost.setAdminVerificationStatus(true);

        } else {
            currentPost.setAdminVerified(false);
            currentPost.setVerificationStatusMessage("Rejected");
        }

        
        //else do nothing
        communityRepository.save(currentPost);

        return "done";
    }

    @Override
    public String deletePost(String postId,String employeeCode) throws  CommunityException{

        if (postId != null) {
            // check if the employee exists
            Community existingPost = communityRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Community post not found"));
            if (employeeCode.equals(existingPost.getEmployee_code())) {
                List<ContentDetails> existingPostContents = existingPost.getContents();
           for(ContentDetails contentDetails : existingPostContents) {
               if (contentDetails != null) {
                   Path existingFilePath = Paths.get(uploadDirectory + contentDetails.getContentName());
                   try {
                       Files.delete(existingFilePath);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
                // delete the employee by id
                communityRepository.deleteById(postId);
                // return a dummy employee with the deleted id
                return "Post "+postId+ " Deleted Successfully" ;
            } else {
                throw new CommunityException("INVALID" + postId);
            }
        } else {
            throw new CommunityException("PostId Cannot be null");
        }
    }

    @Override
    public String updatePost(String postId, CommunityUpdateDto communityUpdateDto,String employeeCode) throws CommunityException, ServiceNotFoundException {

        if(postId!=null && communityRepository.existsById(postId)){

              Community existingPost=communityRepository.findById(postId).orElseThrow(()->new ClassCastException("werftgyhj"));
              if(employeeCode.equals(existingPost.getEmployee_code())) {
                  existingPost.setTitle(communityUpdateDto.getTitle());
                  existingPost.setDescription(communityUpdateDto.getDescription());
                  existingPost.setDate_time(LocalDateTime.now());
                  existingPost.setAdminVerified(false);
                  existingPost.setVerificationStatusMessage("Pending");
                  existingPost.setLikedEmployee(communityRepository.findById(postId).get().getLikedEmployee() == null ? new ArrayList<>() : communityRepository.findById(postId).get().getLikedEmployee());

                  try {
                      Date date = new Date();
                      LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                      String message ="\nUpdated Post"+
                              "\nFrom - " + existingPost.getEmployee_code() +
                                      "\nTitle - " + existingPost.getTitle() +
                                      "\n" + "Click here for more info: \n";
                      NotificationDto notificationDto = new NotificationDto();
                      notificationDto.setMessage(message);
                      notificationDto.setRecipient("titango129@gmail.com");
                      notificationDto.setTimestamp(localDateTime);

                      pushNotification(notificationDto);
                  }
                  finally {
                      System.out.println("Connection refused");
                      communityRepository.save(existingPost);
                  }

//                  communityRepository.save(existingPost);
              }
              else {
                  return "Employee code invalid";
              }
        }

        return "Post updated Successfully";
    }


    @Transactional
    @Override
    public String updateMedia(String postId, List<MultipartFile> files, String employeeCode, String title, String description) throws CommunityException, IOException, ServiceNotFoundException {
        Community existingPost = communityRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Community post not found"));

        if (employeeCode.equals(existingPost.getEmployee_code())) {
            // Delete existing ContentDetails records
            List<ContentDetails> existingPostContents = existingPost.getContents();
            for (ContentDetails contentDetails : existingPostContents) {
                if (contentDetails != null) {
                    // Delete the ContentDetails record from the database
                    contentDetailsRepository.delete(contentDetails);
                    // Optionally, delete the corresponding file from the file system
                    Path existingFilePath = Paths.get(uploadDirectory + contentDetails.getContentName());
                    try {
                        Files.delete(existingFilePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Create new ContentDetails records for the updated files
            List<ContentDetails> contents = new ArrayList<>();
            FileTypeIdentifier identifier = new FileTypeIdentifier();
            for (MultipartFile file : files) {
                try {
                    // Get the original filename of the uploaded file
                    String originalFilename = file.getOriginalFilename();
                    ContentDetails contentDetails = new ContentDetails();
                    contentDetails.setContentName(originalFilename);
                    assert originalFilename != null;
                    contentDetails.setContentType(FileTypeIdentifier.identifyFileType(originalFilename));

                    // Save the uploaded file to the upload directory
                    Path path = Paths.get(uploadDirectory + originalFilename);
                    Files.write(path, file.getBytes());

                    contents.add(contentDetails);
                } catch (IOException e) {
                    // Handle error if file upload fails (you can modify this as needed)
                    return "Error uploading file: " + e.getMessage();
                }
            }

            // Set the new ContentDetails records for the Community entity
            existingPost.setContents(contents);
            existingPost.setTitle(title);
            existingPost.setDescription(description);
            existingPost.setAdminVerified(false);
            existingPost.setVerificationStatusMessage("Pending");

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message ="\nUpdated Post"+
                        "\nFrom - " + existingPost.getEmployee_code() +
                                "\nTitle - " + existingPost.getTitle() +
                                "\n" + "Click here for more info: \n";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient("titango129@gmail.com");
                notificationDto.setTimestamp(localDateTime);

                pushNotification(notificationDto);
            }
            finally {
                System.out.println("Connection refused");
                communityRepository.save(existingPost);
            }
            // Save the updated Community entity
//            communityRepository.save(existingPost);
        } else {
            return "Employee code invalid";
        }

        return "Media post updated successfully";
    }

    @Override
    public List<CommunityGetDto> getMyPost(String employeeCode) throws CommunityException {
        List<CommunityGetDto> newList = new ArrayList<>();
        List<CommunityGetDto> dtoList = communityRepository.findAll().stream().map(p -> {
            // Integer likeCount = p.getLikedEmployee().size();
            CommunityGetDto communityGetDto = null;
            if (p.getEmployee_code().equals(employeeCode)) {
                List<String> likedEmployee = p.getLikedEmployee();
                communityGetDto = CommunityGetDto.builder()
                        .Id(p.getPost_id())
                        .title(p.getTitle())
                        .employee_code(p.getEmployee_code())
                        .likeCount((long) likedEmployee.size())
                        .likedEmployee(p.getLikedEmployee())
                        .contents(p.getContents())
                        .description(p.getDescription())
                        .adminVerified(p.getAdminVerified())
                        .date_time(p.getDate_time())
                        .verificationStatusMessage(p.getVerificationStatusMessage())
//                        .adminVerificationStatus(p.getAdminVerificationStatus())

                        .build();
                newList.add(communityGetDto);
            }
            return communityGetDto;


        }).collect(Collectors.toList());
        System.out.println(dtoList);
        Collections.sort(newList, Comparator.comparing(CommunityGetDto::getDate_time).reversed());
        return newList;


    }
    @Override
    public void pushNotification(NotificationDto notificationDto) throws ServiceNotFoundException {

        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.1.78:8096/send")
                .build().post().uri("/email").bodyValue(notificationDto).retrieve().toBodilessEntity().block();
    }



//    @Override
//    public String updateMedia(String postId, List<MultipartFile> files, String employeeCode,String title, String  description) throws CommunityException, IOException {
//        Community existingPost = communityRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Community post not found"));
////     for(ContentDetails contentDetails :    existingPost.getContents()){
////                 contentDetailsRepository.remove(contentDetails);
////         };
//
//
//        if (employeeCode.equals(existingPost.getEmployee_code())) {
//            existingPost.setTitle(title);
//            existingPost.setDescription(description);
//            existingPost.setAdminVerified(false);
//            existingPost.setVerificationStatusMessage("Pending");
////            existingPost.setAdminVerificationStatus(false);
//
//
//           List<ContentDetails> existingPostContents = existingPost.getContents();
//           for(ContentDetails contentDetails : existingPostContents) {
//               if (contentDetails != null) {
//                   Path existingFilePath = Paths.get(uploadDirectory + contentDetails.getContentName());
//                   try {
//                       Files.delete(existingFilePath);
//                   } catch (IOException e) {
//                       e.printStackTrace();
//                   }
//               }
//           }
//
//
//            List<ContentDetails>contents=new ArrayList<>();
//            FileTypeIdentifier identifier = new FileTypeIdentifier();
//            for (MultipartFile file : files) {
//                try {
//                    // Get the original filename of the uploaded file
//                    String originalFilename = file.getOriginalFilename();
//                    ContentDetails contentDetails=new ContentDetails();
//                    contentDetails.setContentName(originalFilename);
//                    assert originalFilename != null;
//                    contentDetails.setContentType(FileTypeIdentifier.identifyFileType(originalFilename));
//
//                    // Save the uploaded file to the upload directory
//                    Path path = Paths.get(uploadDirectory + originalFilename);
//                    Files.write(path, file.getBytes());
//
//
//                    contents.add(contentDetails);
//
//
//                } catch (IOException e) {
//                    // Handle error if file upload fails (you can modify this as needed)
//                    return "Error uploading file: " + e.getMessage();
//                }
//            }
//            existingPost.setContents(contents);
//            communityRepository.save(existingPost);
//
//        }else{
//            return "Employee code invalid";
//        }
//
//        return "Media post updated successfully";
//
//
//    }



}


