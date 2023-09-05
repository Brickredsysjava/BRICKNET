package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.dto.CommunityGetDto;
import org.example.dto.CommunityPostDto;
import org.example.dto.CommunityUpdateDto;
import org.example.dto.NotificationDto;
import org.example.exception.CommunityException;
import org.example.model.Community;
import org.example.service.CommunityServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/communityPost")
@CrossOrigin("*")
public class CommunityController {

@Autowired
private CommunityServiceImplementation communityService;

    @PostMapping("/addText")
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
    public ResponseEntity<String> addLike(@RequestParam String post_id, @RequestParam String employee_code,@RequestParam Boolean like) throws CommunityException {

        String res=communityService.addlike(post_id,employee_code,like);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{post_id}")
    @Operation(summary = "For delete the community post")
    public ResponseEntity<String> deletePost(@PathVariable("post_id") String post_id,@RequestParam String employeeCode) throws CommunityException {
        return new ResponseEntity<>(communityService.deletePost(post_id,employeeCode),HttpStatus.OK);
    }

    @PostMapping("/Update/{post_id}")
    @Operation(summary = "For update the community post")
    public ResponseEntity<String> updatePost(@PathVariable("post_id") String post_id,@RequestBody CommunityUpdateDto communityUpdateDto,@RequestParam String employeeCode) throws CommunityException, ServiceNotFoundException {
        return new ResponseEntity<>(communityService.updatePost(post_id,communityUpdateDto,employeeCode),HttpStatus.OK);
    }

    @GetMapping("getMyPost/{employeeCode}")
    @Operation(summary =  "For getting my post")
    public  ResponseEntity<List<CommunityGetDto>> getMyPost(@PathVariable("employeeCode") String employeeCode) throws CommunityException {
        List<CommunityGetDto> e1 = communityService.getMyPost(employeeCode);
        return new ResponseEntity<>( e1, HttpStatus.OK);
    }

    @GetMapping("/getAllPostNeedToVerified")
    @Operation(summary = "For getting the post to be verified")
    public ResponseEntity<List<CommunityGetDto>> getAllPostNeedToVerified() throws CommunityException{
        List<CommunityGetDto> e1 = communityService.getAllPostNeedToVerified();
        return new ResponseEntity<>( e1, HttpStatus.OK);
    }



    @PostMapping("/postVerification")
    @Operation(summary = "For verification from the admin")
    public ResponseEntity<String> postVerification(@RequestParam String post_id,@RequestParam Boolean adminVerified) throws CommunityException {

        String res=communityService.postVerification(post_id,adminVerified);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
