package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.*;
import org.example.exception.CommunityException;
import org.example.model.Community;
import org.example.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImplementation implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void addPost(Community community) throws CommunityException , ServiceNotFoundException{

        if (community != null) {
            //Notification cod
            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "\nFrom - " + community.getEmployeeCode() +
                                "\nTitle - " + community.getTitle() +
                                "\n" + "Click here for more info: \n";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient("titango129@gmail.com");
                notificationDto.setTimeStamp(localDateTime);

                pushNotification(notificationDto);
            }catch(Exception e){
                System.out.println("Connection refused");
            }
            finally {

                communityRepository.save(community);
            }

        } else {
            throw new CommunityException("Details are not exist");

        }
    }

    @Override
    public List<CommunityGetDto> getAllPosts() throws CommunityException{
        List<CommunityGetDto> newDtoList = new ArrayList<>();
        List<CommunityGetDto> dtoList = communityRepository.findAll().stream().map(p -> {
            CommunityGetDto communityGetDto = null;
            if (p.getAdminVerified() == true) {
                List<String> likedEmployee = p.getLikedEmployee();
                communityGetDto = CommunityGetDto.builder()
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        .employeeCode(p.getEmployeeCode())
                        .username(p.getUsername())
                        .dateTime(p.getDateTime())
                        .likeCount((long) likedEmployee.size())
                        .likedEmployee(p.getLikedEmployee())
                        .description(p.getDescription())
                        .fileName(p.getFileName())
                        .adminVerified(p.getAdminVerified())
                        .verificationStatusMessage(p.getVerificationStatusMessage())
//                        .adminVerificationStatus(p.getAdminVerificationStatus())
                        .build();
                newDtoList.add(communityGetDto);
            }
            return communityGetDto;


        }).collect(Collectors.toList());

        Collections.sort(newDtoList, Comparator.comparing(CommunityGetDto::getDateTime).reversed());

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
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        .username(p.getUsername())
                        .employeeCode(p.getEmployeeCode())
                        .dateTime(p.getDateTime())
                        .likeCount((long) likedEmployee.size())
                        .likedEmployee(p.getLikedEmployee())
                        .description(p.getDescription())
                        .fileName(p.getFileName())
                        .adminVerified(p.getAdminVerified())
                        .verificationStatusMessage(p.getVerificationStatusMessage())
//                        .adminVerificationStatus(p.getAdminVerificationStatus())
                        .build();
                newDtoList.add(communityGetDto);
            }
            return communityGetDto;


        }).toList();

        // Sorting the newDtoList by date_time in descending order
        newDtoList.sort(Comparator.comparing(CommunityGetDto::getDateTime).reversed());


            return newDtoList;


    }


    @Override
    public String addlike(CommunityAddLikeDto communityAddLikeDto) throws CommunityException {
        Community currentPost = communityRepository.findById(communityAddLikeDto.getPostId()).orElseThrow(() -> new CommunityException("post not found"));


            List<String> likedEmployee = currentPost.getLikedEmployee();
            if (likedEmployee == null) {
                //initialize with an empty list
                likedEmployee = new ArrayList<>();
            }
            boolean alreadyLiked = likedEmployee.contains(communityAddLikeDto.getEmployeeCode());

            if (communityAddLikeDto.getLike() && !alreadyLiked) {
                //add a new like
                likedEmployee.add(communityAddLikeDto.getEmployeeCode());

            } else if (!communityAddLikeDto.getLike() && alreadyLiked) {
                //remove an existing like
                likedEmployee.remove(communityAddLikeDto.getEmployeeCode());

            }
            //else do nothing

            currentPost.setLikedEmployee(likedEmployee);
            communityRepository.save(currentPost);
        return communityAddLikeDto.getEmployeeCode()+" have successfully liked the post";
    }

    @Override
    public String postVerification(AdminPostVerificationDto adminPostVerificationDto) throws CommunityException, ServiceNotFoundException {
        Community currentPost = communityRepository.findById(adminPostVerificationDto.getPostId()).orElseThrow(() -> new CommunityException("post not found"));


        if (adminPostVerificationDto.getAdminVerified()) {
            currentPost.setAdminVerified(true);
            currentPost.setVerificationStatusMessage("Approved");
//            currentPost.setAdminVerificationStatus(true);

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message ="\nHi "+currentPost.getEmployeeCode()+"!"+
                        "\nYour Post has been Approved"+
                                "\nTitle - " + currentPost.getTitle()
                        +"\nFrom Admin,";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient(getEmailIdByUserName(currentPost.getEmployeeCode()));
                notificationDto.setTimeStamp(localDateTime);

                pushNotification(notificationDto);
            }
            catch (Exception e){
                System.out.println("Connection refused");
            }
            finally {
                communityRepository.save(currentPost);
            }

        } else {
            currentPost.setAdminVerified(false);
            currentPost.setVerificationStatusMessage("Rejected");

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message ="Hi"+currentPost.getEmployeeCode()+"!"+
                        "\nYour Post has been Rejected"+
                                "\nTitle - " + currentPost.getTitle()
                        +"\nFrom Admin,";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient(getEmailIdByUserName(currentPost.getEmployeeCode()));
                notificationDto.setTimeStamp(localDateTime);

                pushNotification(notificationDto);
            }catch (Exception e){
                System.out.println("Connection refused");
            }
            finally {
                communityRepository.save(currentPost);
            }

        }

        return "Verification done";
    }

    @Override
    public String deletePost(CommunityPostDeleteDto communityPostDeleteDto) throws  CommunityException{

        if (communityPostDeleteDto.getPostId() != null) {
            // check if the employee exists
            Community existingPost = communityRepository.findById(communityPostDeleteDto.getPostId()).orElseThrow(() -> new EntityNotFoundException("Community post not found"));
            if (communityPostDeleteDto.getEmployeeCode().equals(existingPost.getEmployeeCode())) {

                communityRepository.deleteById(communityPostDeleteDto.getPostId());

                return "Post "+communityPostDeleteDto.getPostId()+ " Deleted Successfully" ;
            } else {

               throw new CommunityException("INVALID" + communityPostDeleteDto.getPostId());

            }
        } else {

            throw new CommunityException("PostId Cannot be null");
        }
    }

    @Override
    public String updatePost(CommunityUpdateDto communityUpdateDto) throws CommunityException, ServiceNotFoundException {

        if(communityUpdateDto.getPostId()!=null && communityRepository.existsById(communityUpdateDto.getPostId())){

              Community existingPost=communityRepository.findById(communityUpdateDto.getPostId()).orElseThrow(()->new ClassCastException("Post not found"));
              if(communityUpdateDto.getEmployeeCode().equals(existingPost.getEmployeeCode())) {
                  existingPost.setTitle(communityUpdateDto.getTitle());
                  existingPost.setDescription(communityUpdateDto.getDescription());
                  existingPost.setFileName(communityUpdateDto.getFileName());
                  existingPost.setDateTime(LocalDateTime.now());
                  existingPost.setAdminVerified(false);
                  existingPost.setVerificationStatusMessage("Pending");
                  existingPost.setLikedEmployee(communityRepository.findById(communityUpdateDto.getPostId()).get().getLikedEmployee() == null ? new ArrayList<>() : communityRepository.findById(communityUpdateDto.getPostId()).get().getLikedEmployee());

                  try {
                      Date date = new Date();
                      LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                      String message ="\nUpdated Post"+
                              "\nFrom - " + existingPost.getEmployeeCode() +
                                      "\nTitle - " + existingPost.getTitle() +
                                      "\n" + "Click here for more info: \n";
                      NotificationDto notificationDto = new NotificationDto();
                      notificationDto.setMessage(message);
                      notificationDto.setRecipient("titango129@gmail.com");
                      notificationDto.setTimeStamp(localDateTime);

                      pushNotification(notificationDto);
                  }catch (Exception e){
                      System.out.println("Connection refused");
                  }
                  finally {
                      communityRepository.save(existingPost);
                  }
              }
              else {
                  return "Employee code invalid";
              }
        }

        return "Post updated Successfully";
    }

    @Override
    public List<CommunityGetDto> getMyPost(String employeeCode) throws CommunityException {
        List<CommunityGetDto> newList = new ArrayList<>();
        List<CommunityGetDto> dtoList = communityRepository.findAll().stream().map(p -> {
            // Integer likeCount = p.getLikedEmployee().size();
            CommunityGetDto communityGetDto = null;
            if (p.getEmployeeCode().equals(employeeCode)) {
                List<String> likedEmployee = p.getLikedEmployee();
                communityGetDto = CommunityGetDto.builder()
                        .postId(p.getPostId())
                        .title(p.getTitle())
                        .employeeCode(p.getEmployeeCode())
                        .username(p.getUsername())
                        .likeCount((long) likedEmployee.size())
                        .likedEmployee(p.getLikedEmployee())
                        .fileName(p.getFileName())
                        .description(p.getDescription())
                        .adminVerified(p.getAdminVerified())
                        .dateTime(p.getDateTime())
                        .verificationStatusMessage(p.getVerificationStatusMessage())
                        .build();
                newList.add(communityGetDto);
            }
            return communityGetDto;


        }).collect(Collectors.toList());
        System.out.println(dtoList);
        Collections.sort(newList, Comparator.comparing(CommunityGetDto::getDateTime).reversed());
        return newList;

    }
    @Override
    public void pushNotification(NotificationDto notificationDto) throws ServiceNotFoundException {

        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.0.9:8084/send")
                .build().post().uri("/email").bodyValue(notificationDto).retrieve().toBodilessEntity().block();
    }

    @Override
    public String getEmailIdByUserName(String username) {
        HashMap<String, String> emailIdHashMap = new HashMap<>();
        emailIdHashMap.put("BR198", "piyushrai558@gmail.com");
        emailIdHashMap.put("Piyush", "piyushrai558@gmail.com");
        emailIdHashMap.put("Pankaj", "karl98perfect@gmail.com");
        emailIdHashMap.put("Debayan", "tubbu32@gmail.com");
        String email = emailIdHashMap.get(username);
        return email;
    }
}


