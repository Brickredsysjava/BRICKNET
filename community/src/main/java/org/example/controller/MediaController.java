package org.example.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.example.exception.CommunityException;
import org.example.model.Community;
import org.example.model.ContentDetails;
import org.example.repository.CommunityRepository;
import org.example.service.CommunityServiceImplementation;
import org.example.service.FileTypeIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.ServiceNotFoundException;


@RestController
@CrossOrigin("*")
public class MediaController {

    @Autowired
    private CommunityServiceImplementation communityService;

    @Autowired
    private CommunityRepository communityRepository;


    @Value("${uploadDirectory}")
    public    String uploadDirectory ;

    @PostMapping("/images/upload-api")
    @ResponseBody
    public String handleMediaUpload(@RequestParam String title,
                                    @RequestParam String employeeCode,
                                    @RequestParam String description,
                                    @RequestPart List<MultipartFile> files) throws CommunityException, ServiceNotFoundException {

        Community communityPost = Community.builder()
                .title(title)
                .employee_code(employeeCode)
                .description(description)
                .date_time(LocalDateTime.now())
                .build();

        List<ContentDetails>contents=new ArrayList<>();
        FileTypeIdentifier identifier = new FileTypeIdentifier();
        for (MultipartFile file : files) {
            try {
                // Get the original filename of the uploaded file
                String originalFilename = file.getOriginalFilename();
                ContentDetails contentDetails=new ContentDetails();
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
        communityPost.setContents(contents);
        communityPost.setAdminVerified(false);
//        communityPost.setAdminVerificationStatus(false);
        communityPost.setVerificationStatusMessage("Pending");
        communityService.addPostMedia(communityPost);
        return "Files uploaded successfully.";
    }

    @GetMapping(value = "/images/{filename:.+}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE  ,MediaType.ALL_VALUE})
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws CommunityException{
        // Load the image file from the upload directory

        Path path = Paths.get("./uploads/" + filename);
        Resource resource = null;

        try {
            resource = new UrlResource(path.toUri());
            if(resource.exists()==false){
                throw new CommunityException("File not exist");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Return the image file as a response
        return ResponseEntity.ok(resource);
    }

    @PostMapping("updateMedia/{post_id}")
    public ResponseEntity<String> updateMedia(@PathVariable("post_id") String post_id,@RequestPart List<MultipartFile> files,@RequestParam String employeeCode,@RequestParam String title,@RequestParam String description) throws CommunityException, IOException, ServiceNotFoundException {

        return new ResponseEntity<>(communityService.updateMedia(post_id,files,employeeCode,title,description), HttpStatus.OK);
    }




//    @GetMapping("/images")
//    public ResponseEntity<Resource> viewImages(@RequestParam("filename") String filename) {
//        // Define the directory where you stored the uploaded files
//
//        try {
//            // Read the file from the upload directory
//            Path path = Paths.get(uploadDirectory + filename);
//            Resource resource = new UrlResource(path.toUri());
//
//            // Return the file as a response
//            if (resource.exists() || resource.isReadable()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.IMAGE_JPEG)
//                        .body(resource);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (MalformedURLException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
//    @GetMapping("/images/list")
//    @ResponseBody
//    public List<String> listImages() {
//        // Define the directory where you stored the uploaded files
//
//        // Get the list of filenames in the upload directory
//        File uploadDir = new File(uploadDirectory);
//        String[] filenames = uploadDir.list();
//
//        // Return the list of filenames as a response
//        return Arrays.asList(filenames);
//    }
//    @GetMapping("/images/all")
//    public List<String> listAllImages() {
//        // Define the directory where you stored the uploaded files
//
//
//        try {
//            // List all the files in the upload directory
//            List<String> fileNames = Arrays.stream(new File(uploadDirectory).listFiles())
//                    .filter(file -> !file.isDirectory())
//                    .map(File::getName)
//                    .collect(Collectors.toList());
//
//            // Return the list of file names as a response
//            return fileNames;
//        } catch (Exception e) {
//            // Return an error response if there was an exception
//            return Collections.emptyList();
//        }
//    }








}
