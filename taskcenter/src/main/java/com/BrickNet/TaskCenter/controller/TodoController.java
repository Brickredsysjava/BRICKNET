package com.BrickNet.TaskCenter.controller;


import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.serviceImpl.TodoServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/to-do")
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService;

    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/create-to-do")
    public ResponseEntity<TodoDTO> addToDo( @RequestBody TodoDTO todoDTO) throws TodoException , ServiceNotFoundException {
         TodoDTO savedTodo = todoService.addToDo(todoDTO);
         return new ResponseEntity<TodoDTO>(savedTodo,HttpStatus.CREATED);
    }

    @GetMapping("/show-created-to-do/{empId}")
    public ResponseEntity<List<TodoDTO>> showCreatedToDo(@PathVariable String empId) throws TodoException{
        return new ResponseEntity<List<TodoDTO>>(todoService.showCreatedToDo(empId), HttpStatus.CREATED.OK);
    }

    @PutMapping("/update-created-to-do/{id}")
    public ResponseEntity<TodoDTO> updateCreatedToDo(@PathVariable String id,@RequestBody TodoDTO todoDTO) throws TodoException {
        return new ResponseEntity<TodoDTO>(todoService.updateCreatedToDo(id,todoDTO),HttpStatus.CREATED.OK);
    }

    @DeleteMapping("/delete-Created-To-Do/{assignedBy}/{todoTaskName}/{assignedTo}")
    public String deleteCreatedToDo( @PathVariable String assignedBy,@PathVariable String todoTaskName,@PathVariable String assignedTo) throws TodoException{
         todoService.deleteCreatedToDo(assignedBy,todoTaskName,assignedTo);
         return "Deleted Successfully";
    }

    @DeleteMapping("/delete-To-Do/{id1}/{id2}")
    public String deleteToDo(@RequestParam("id1") String id1, @RequestParam("id2") String id2) throws TodoException{
        todoService.deleteToDo(id1,id2);
        return "Delete Successfully";
    }

}
