package com.BrickNet.TaskCenter.controller;

import com.BrickNet.TaskCenter.dto.AllDTO;
import com.BrickNet.TaskCenter.dto.StoriesDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.serviceImpl.AllServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoriesController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AllServiceImpl allService;

    private String baseUrl = "http://192.168.1.107:8089/todo/myTask";

    @GetMapping(value = "/getStories/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AllDTO>> getStories(@PathVariable String userId)throws TodoException {
        String url = baseUrl + "?userId=" + userId;
        ResponseEntity<List<StoriesDTO>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StoriesDTO>>() {}
        );
        List<StoriesDTO> storiesDTOList = responseEntity.getBody();
        return new ResponseEntity<>(allService.getAll(storiesDTOList,userId), HttpStatus.CREATED);
    }

}
