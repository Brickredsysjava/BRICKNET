package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.dto.*;
import org.example.exception.CommunityException;
import org.example.model.Community;
import org.example.service.CommunityServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/communityPost")
@CrossOrigin("*")
public class CommunityPostController {

@Autowired
private CommunityServiceImplementation communityService;

    @PostMapping("/addPost")
    //@Operation(summary = "Save File to the disk", operationId = "1", description = "Save the file to the disk y passing json value")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@Valid @RequestBody CommunityPostDto communityPostDto, HttpServletRequest request) throws CommunityException, ServiceNotFoundException {

        communityPostDto.setEmployeeCode((String)request.getAttribute("employeeCode"));

        Community community = Community.builder()
                .employeeCode(communityPostDto.getEmployeeCode())
                .title(communityPostDto.getTitle())
                .dateTime(LocalDateTime.now())
                .fileName(communityPostDto.getFileName())
                .description(communityPostDto.getDescription())
                .adminVerified(false)
//                .adminVerificationStatus(false)
                .likedEmployee(new ArrayList<>())
                .verificationStatusMessage("Pending")
                .build();

        communityService.addPost(community);

    }

    @GetMapping("/getAllPosts")
    @Operation(summary = "Display all posts" )
    public ResponseEntity<List<CommunityGetDto>> getAllPosts() throws CommunityException{

        List<CommunityGetDto> e1 = communityService.getAllPosts();
        return new ResponseEntity<>( e1, HttpStatus.OK);
    }

    @PostMapping("/addLike")
    @Operation(summary = "For like the community post")
    public ResponseEntity<String> addLike(@RequestBody CommunityAddLikeDto communityAddLikeDto,HttpServletRequest request) throws CommunityException {

        communityAddLikeDto.setEmployeeCode((String) request.getAttribute("employeeCode"));
        String res=communityService.addlike(communityAddLikeDto);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping("/deletePost")
    @Operation(summary = "For delete the community post")
    public ResponseEntity<String> deletePost(@RequestBody CommunityPostDeleteDto communityPostDeleteDto,HttpServletRequest request) throws CommunityException {
        try {
            communityPostDeleteDto.setEmployeeCode((String) request.getAttribute("employeeCode"));
            return new ResponseEntity<>(communityService.deletePost(communityPostDeleteDto), HttpStatus.OK);
        }catch (CommunityException communityException){
            return new ResponseEntity<>("Post not found",HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/updatePost")
    @Operation(summary = "For update the community post")
    public ResponseEntity<String> updatePost( @Valid @RequestBody CommunityUpdateDto communityUpdateDto) throws CommunityException, ServiceNotFoundException {
        return new ResponseEntity<>(communityService.updatePost(communityUpdateDto),HttpStatus.OK);
    }

    @GetMapping("/getMyPost")
    @Operation(summary =  "For getting my post")
    public  ResponseEntity<?> getMyPost(HttpServletRequest request) throws CommunityException {
         String employeeCode = (String) request.getAttribute("employeeCode");
        List<CommunityGetDto> e1 = communityService.getMyPost(employeeCode);
        if(!e1.isEmpty()) {
            return new ResponseEntity<>(e1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No post have posted by "+employeeCode,HttpStatus.ALREADY_REPORTED);
        }
    }

}
