package com.bricknet.communityservice.controller;

import com.bricknet.communityservice.dto.CommunityPostDto;
import com.bricknet.communityservice.exception.CommunityException;
import com.bricknet.communityservice.model.CommunityPost;
import com.bricknet.communityservice.serviceImplementation.CommunityServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
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
    public void addPostText(@RequestBody CommunityPostDto communityPostDto)  throws CommunityException,IOException, SQLException {
        communityService.addPostText(communityPostDto);

    }

    @PostMapping("/addMedia")
    //@Operation(summary = "Save File to the disk", operationId = "1", description = "Save the file to the disk y passing json value")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPostMedia(@RequestParam MultipartFile file)  throws CommunityException,IOException, SQLException {

        communityService.addPostMedia( file);

    }

    @GetMapping("/getAllPosts")
    @Operation(summary = "Display all posts" )
    public ResponseEntity<List<CommunityPost>> getAllPosts() throws CommunityException {

        List<CommunityPost> e1 = communityService.getAllPosts();
        return new ResponseEntity<List<CommunityPost>>(e1, HttpStatus.OK);
    }

//    @PostMapping("/upload")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void uploadImage (@RequestParam("image")MultipartFile file)throws IOException{
//         communityService.uploadImage(file);
//
//    }
//
//    @GetMapping("/{filename}")
//    @ResponseStatus(HttpStatus.FOUND)
//    public void downloadImage(@PathVariable String filename){
//         communityService.downloadImage(filename);
//
//    }



}
