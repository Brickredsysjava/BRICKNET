package com.BrickNet.TaskCenter.controller;


import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.serviceImpl.TodoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> addToDo( @RequestBody TodoDTO todoDTO, HttpServletRequest request) throws TodoException , ServiceNotFoundException {
        try{
            todoDTO.setEmployeeAssignedBy((String) request.getAttribute("employeeCode"));
            TodoDTO savedTodo = todoService.addToDo(todoDTO);
            return new ResponseEntity<TodoDTO>(savedTodo, HttpStatus.CREATED);
        }catch (TodoException todoException) {
            return new ResponseEntity<>("Data Not Found",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/show-created-to-do")
    public ResponseEntity<?> showCreatedToDo(HttpServletRequest request) throws TodoException{
        try{
            String employeeCode = (String) request.getAttribute("employeeCode");
            return new ResponseEntity<List<TodoDTO>>(todoService.showCreatedToDo(employeeCode), HttpStatus.OK);
        }catch (TodoException todoException) {
            return new ResponseEntity<>("Data Not Found",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-created-to-do")
    public ResponseEntity<?> updateCreatedToDo(@RequestParam("id") String id, @RequestBody TodoDTO todoDTO, HttpServletRequest request) throws TodoException {
        try{
            todoDTO.setEmployeeAssignedBy((String) request.getAttribute("employeeCode"));
            return new ResponseEntity<TodoDTO>(todoService.updateCreatedToDo(id, todoDTO), HttpStatus.CREATED);
        }catch (TodoException todoException) {
            return  new ResponseEntity<>("Data Not Found",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-To-Do")
    public ResponseEntity<String> deleteToDo(@RequestParam("id") String id, HttpServletRequest request) throws TodoException{
       try {
           todoService.deleteToDo(id);
           return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
       }catch (TodoException todoException){
           return  new ResponseEntity<>("Data Not Found",HttpStatus.BAD_REQUEST);
       }

    }

    @GetMapping("/test")
    public String test(){
        return "Hello !! THis is taskcenter";
    }
}
