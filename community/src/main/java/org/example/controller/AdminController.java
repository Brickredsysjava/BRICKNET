package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dto.CommunityGetDto;
import org.example.exception.CommunityException;
import org.example.service.CommunityServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/communityPost")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private CommunityServiceImplementation communityService;
    @PostMapping("/postVerification")
    @Operation(summary = "For verification from the admin")
    public ResponseEntity<String> postVerification(@RequestParam String post_id, @RequestParam Boolean adminVerified) throws CommunityException, ServiceNotFoundException {

        String res=communityService.postVerification(post_id,adminVerified);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/getAllPostNeedToVerified")
    @Operation(summary = "For getting the post to be verified")
    public ResponseEntity<List<CommunityGetDto>> getAllPostNeedToVerified() throws CommunityException{
        List<CommunityGetDto> e1 = communityService.getAllPostNeedToVerified();
        return new ResponseEntity<>( e1, HttpStatus.OK);
    }
}
