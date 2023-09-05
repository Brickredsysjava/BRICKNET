package com.BrickNet.TaskCenter.controller;


import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.serviceImpl.TodoServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/to-do")
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService;

    @PostMapping("/create-to-do")
    public String addToDo( @RequestBody TodoDTO todoDTO) throws TodoException {
         todoService.addToDo(todoDTO);
         return "Inserted Successfully";
    }

    @GetMapping("/show-created-to-do/{empId}")
    public ResponseEntity<List<TodoDTO>> showCreatedToDo(@PathVariable String empId) throws TodoException{
        return new ResponseEntity<List<TodoDTO>>(todoService.showCreatedToDo(empId), HttpStatus.CREATED.OK);
    }

    @PutMapping("/update-created-to-do/{id}")
    public ResponseEntity<TodoDTO> updateCreatedToDo(@PathVariable Integer id,@RequestBody TodoDTO todoDTO) throws TodoException {
        return new ResponseEntity<TodoDTO>(todoService.updateCreatedToDo(id,todoDTO),HttpStatus.CREATED.OK);
    }

    @DeleteMapping("/delete-Created-To-Do/{empId}/{todoTaskName}/{assignedTo}")
    public String deleteCreatedToDo( @PathVariable String empId,@PathVariable String todoTaskName,@PathVariable String assignedTo) throws TodoException{
         todoService.deleteCreatedToDo(empId,todoTaskName,assignedTo);
         return "Deleted Successfully";
    }

}
