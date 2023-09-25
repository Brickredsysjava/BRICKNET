package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import java.util.List;

@RestController
@RequestMapping("/communityPost")
@CrossOrigin("*")
public class TextPostController {

@Autowired
private CommunityServiceImplementation communityService;

    @PostMapping("/addTextPost")
    //@Operation(summary = "Save File to the disk", operationId = "1", description = "Save the file to the disk y passing json value")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPostText(@Valid @RequestBody CommunityPostDto communityPostDto) throws CommunityException, ServiceNotFoundException {
        Community community = Community.builder()
                .employee_code(communityPostDto.getEmployee_code())
                .title(communityPostDto.getTitle())
                .date_time(LocalDateTime.now())
                .description(communityPostDto.getDescription())
                .adminVerified(false)
//                .adminVerificationStatus(false)
                .likedEmployee(new ArrayList<>())
                .verificationStatusMessage("Pending")
                .build();

        communityService.addPostText(community);

    }

    @GetMapping("/getAllPosts")
    @Operation(summary = "Display all posts" )
    public ResponseEntity<List<CommunityGetDto>> getAllPosts() throws CommunityException{

        List<CommunityGetDto> e1 = communityService.getAllPosts();
        return new ResponseEntity<>( e1, HttpStatus.OK);
    }

    @PostMapping("/addLike")
    @Operation(summary = "For like the community post")
    public ResponseEntity<String> addLike(@RequestParam String post_id,@RequestParam String employeeCode, @RequestBody CommunityAddLikeDto communityAddLikeDto) throws CommunityException {

        String res=communityService.addlike(post_id,employeeCode,communityAddLikeDto);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{post_id}")
    @Operation(summary = "For delete the community post")
    public ResponseEntity<String> deletePost(@RequestParam String post_id, @RequestParam String employeeCode) throws CommunityException {
        return new ResponseEntity<>(communityService.deletePost(post_id,employeeCode),HttpStatus.OK);
    }

    @PutMapping("/Update")
    @Operation(summary = "For update the community post")
    public ResponseEntity<String> updatePost( @RequestParam String post_id,@RequestBody CommunityUpdateDto communityUpdateDto,@RequestParam String employeeCode) throws CommunityException, ServiceNotFoundException {
        return new ResponseEntity<>(communityService.updatePost(post_id,communityUpdateDto,employeeCode),HttpStatus.OK);
    }

    @GetMapping("getMyPost/{employeeCode}")
    @Operation(summary =  "For getting my post")
    public  ResponseEntity<List<CommunityGetDto>> getMyPost(@RequestParam String employeeCode) throws CommunityException {
        List<CommunityGetDto> e1 = communityService.getMyPost(employeeCode);
        return new ResponseEntity<>( e1, HttpStatus.OK);
    }







}
