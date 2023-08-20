package com.bricknet.workflow.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/media")
@CrossOrigin("*")
public class MediaController {
    @GetMapping(value = "/images/{filename:.+}", produces = { MediaType.ALL_VALUE})
//  @GetMapping(name="/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        // Load the image file from the upload directory
        Path path = Paths.get("./uploads/" + filename);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Return the image file as a response
        return ResponseEntity.ok(resource);
    }
}
