package com.BrickNet.TaskCenter.controller;

import com.BrickNet.TaskCenter.dto.AllDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.serviceImpl.StoriesTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/stories")
public class StoriesController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StoriesTaskServiceImpl storiesTaskService;

//    private String baseUrl = "http://192.168.1.107:8089/todo/myTask";

    @GetMapping("/getStories")
    public ResponseEntity<?> getStories(@RequestParam("userId") String userId)throws TodoException {
        try{
            return new ResponseEntity<List<AllDTO>>(storiesTaskService.getAll(userId), HttpStatus.CREATED);
        }catch(TodoException todoException) {
            return new ResponseEntity<>("Data Not Found",HttpStatus.BAD_REQUEST);
        }
    }



}
