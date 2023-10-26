package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.dto.AdminPostVerificationDto;
import org.example.dto.CommunityGetDto;
import org.example.exception.CommunityException;
import org.example.service.CommunityServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/communityPost/admin")
public class AdminController {

    @Autowired
    private CommunityServiceImplementation communityService;
    @PostMapping("/postVerification")
    @Operation(summary = "For verification from the admin")
    public ResponseEntity<String> postVerification(@Valid @RequestBody AdminPostVerificationDto adminPostVerificationDto) throws CommunityException, ServiceNotFoundException {

        String res=communityService.postVerification(adminPostVerificationDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getAllPostNeedToVerified")
    @Operation(summary = "For getting the post to be verified")
    public ResponseEntity<?> getAllPostNeedToVerified() throws CommunityException{

            List<CommunityGetDto> e1 = communityService.getAllPostNeedToVerified();
            if(!e1.isEmpty()){
            return new ResponseEntity<>(e1, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No post for the verification",HttpStatus.ALREADY_REPORTED);
        }
    }

    @GetMapping("/test")
    public String getTest(){
        return "Community is up and running";
    }
}
