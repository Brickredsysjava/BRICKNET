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
@RequestMapping("/api/stories")
public class StoriesController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StoriesTaskServiceImpl storiesTaskService;

//    private String baseUrl = "http://192.168.1.107:8089/todo/myTask";

    @GetMapping("/getStories")
    public ResponseEntity<List<AllDTO>> getStories(@RequestParam("userId") String userId)throws TodoException {
//        String url = baseUrl + "?userId=" + userId;
//        ResponseEntity<List<StoriesDTO>> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<StoriesDTO>>() {}
//        );
//        List<StoriesDTO> storiesDTOList = responseEntity.getBody();
        return new ResponseEntity<>(storiesTaskService.getAll(userId), HttpStatus.CREATED);

    }

}
