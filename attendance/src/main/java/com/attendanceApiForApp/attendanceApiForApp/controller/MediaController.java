package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.model.PhotoUpload;
import com.attendanceApiForApp.attendanceApiForApp.service.FileTypeIndentifier;
import com.attendanceApiForApp.attendanceApiForApp.service.PhotoUploadService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/media")
public class MediaController {

    private final PhotoUploadService photoUploadService;
    @Value("${uploadDirectory}")
    public String uploadDirectory ;

    public MediaController(PhotoUploadService photoUploadService) {
        this.photoUploadService = photoUploadService;
    }

    @PostMapping("/images/upload-AttendanceImage")
    @ResponseBody
    public ResponseEntity<Object> handleImageUpload(@RequestParam("files")
                                                    List<MultipartFile> files) throws Exception {
        FileTypeIndentifier fileType = new FileTypeIndentifier();
        PhotoUpload photoUpload = new PhotoUpload();
        List<String> fileNames = new ArrayList<>();
        try {
            for (MultipartFile file : files) {

                String originalFilename = file.getOriginalFilename();
                assert originalFilename != null;
                if (!FileTypeIndentifier.identifyFileType(originalFilename).equals("Unknown")) {
                    // Generate new file name
                    String newFilename = formatFileName(originalFilename);

                    // Save with new file name
                    Path path = Paths.get(uploadDirectory + newFilename);
                    Files.write(path, file.getBytes());
                    fileNames.add(newFilename);
                    photoUpload.setPhotoName(newFilename);
                    photoUpload.setTimestamps(String.valueOf(LocalDateTime.now()));
                    photoUploadService.saveFileName(photoUpload);

                } else {
                    return new ResponseEntity<>("File type is not supported.", HttpStatus.NOT_ACCEPTABLE);
                }
            }
            return new ResponseEntity<>(fileNames, HttpStatus.OK);
        }catch (SizeLimitExceededException sizeLimitExceededException){
            return new ResponseEntity<>("Request size exceeds the allowed limit (10MB). ",HttpStatus.NOT_ACCEPTABLE);
        }
    }


    public static String formatFileName(String inputFileName) {
        // Generate a timestamp

        String timestamp = String.valueOf(LocalDateTime.now()).replaceAll("[^0-9]","");
        // Extract the file extension
        int dotIndex = inputFileName.lastIndexOf(".");
        String extension = "";
        if (dotIndex >= 0 && dotIndex < inputFileName.length() - 1) {
            extension = inputFileName.substring(dotIndex);
        }
        String FileName = null;
        if (dotIndex >= 0) {
            FileName = inputFileName.substring(0, dotIndex);
        }

        // Remove spaces from the file name and the extension
        String fileNameWithoutSpaces = FileName.replaceAll("\\s", "");
        extension = extension.replaceAll("\\s", "");

        // Create the new file name with the extension at the end
        String newFileName = fileNameWithoutSpaces + timestamp + extension;

        return newFileName;
    }

    @GetMapping(value = "/images/{filename:.+}", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE  ,MediaType.ALL_VALUE})
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        // Load the image file from the upload directory
        Path path = Paths.get(uploadDirectory + filename);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Return the image file as a response
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/test")
    public String getTest(){
        return "This is media service";
    }

}
