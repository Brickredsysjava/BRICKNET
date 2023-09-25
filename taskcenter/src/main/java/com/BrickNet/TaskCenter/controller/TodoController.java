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
    public ResponseEntity<TodoDTO> addToDo( @RequestBody TodoDTO todoDTO, HttpServletRequest request) throws TodoException , ServiceNotFoundException {
            todoDTO.setEmployeeAssignedBy((String)request.getAttribute("employeeCode"));
            TodoDTO savedTodo = todoService.addToDo(todoDTO);
            return new ResponseEntity<TodoDTO>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/show-created-to-do")
    public ResponseEntity<List<TodoDTO>> showCreatedToDo(HttpServletRequest request) throws TodoException{
        String employeeCode = (String) request.getAttribute("employeeCode");
        return new ResponseEntity<List<TodoDTO>>(todoService.showCreatedToDo(employeeCode), HttpStatus.OK);
    }

    @PutMapping("/update-created-to-do")
    public ResponseEntity<TodoDTO> updateCreatedToDo(@RequestParam("id") String id, @RequestBody TodoDTO todoDTO, HttpServletRequest request) throws TodoException {
        todoDTO.setEmployeeAssignedBy((String) request.getAttribute("employeeCode"));
        return new ResponseEntity<TodoDTO>(todoService.updateCreatedToDo(id,todoDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-To-Do")
    public ResponseEntity<String> deleteToDo(@RequestParam("id") String id, HttpServletRequest request) throws TodoException{
        todoService.deleteToDo(id);
        return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
    }

}
