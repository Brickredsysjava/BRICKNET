package com.bricknet.workflow.controller;

import com.bricknet.workflow.dto.TodoDto;
import com.bricknet.workflow.model.Task;
import com.bricknet.workflow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@CrossOrigin("*")
public class TodoController {
    @Autowired
    private TaskRepository taskRepository;




}
