package com.BrickNet.TaskCenter.controller;


import com.BrickNet.TaskCenter.dto.PostTodoDTO;
import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;
import com.BrickNet.TaskCenter.model.Todo;
import com.BrickNet.TaskCenter.repository.TodoRepository;
import com.BrickNet.TaskCenter.serviceImpl.TodoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/to-do")
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService;

    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/create-to-do")
    public ResponseEntity<?> addToDo(@Valid @RequestBody PostTodoDTO postTodoDTO) throws TodoException , ServiceNotFoundException {
        try{
//            todoDTO.setEmployeeAssignedBy((String) request.getAttribute("employeeCode"));
            PostTodoDTO savedTodo = todoService.addToDo(postTodoDTO);
            return new ResponseEntity<>(savedTodo, HttpStatus.OK);
        }catch (TodoException todoException) {
            return new ResponseEntity<>("Data Not Found",HttpStatus.valueOf(401));
        }
    }

    @GetMapping("/show-created-to-do")
    public ResponseEntity<List<TodoDTO>> showCreatedToDo(@RequestParam("employeeCode") String employeeCode) throws TodoException{

            return new ResponseEntity<List<TodoDTO>>(todoService.showCreatedToDo(employeeCode), HttpStatus.OK);

    }

    @PutMapping("/update-created-to-do")
    public ResponseEntity<?> updateCreatedToDo(@RequestParam("id") String id,@RequestParam("employeeCode") String employeeCode,@Valid @RequestBody TodoDTO todoDTO) throws TodoException {
        try{
            return new ResponseEntity<TodoDTO>(todoService.updateCreatedToDo(id, employeeCode, todoDTO), HttpStatus.OK);
        }
        catch (TodoException todoException) {
            return new ResponseEntity<>(todoException.getMessage(),HttpStatusCode.valueOf(401));
        }
    }

    @DeleteMapping("/delete-To-Do")
    public ResponseEntity<String> deleteToDo(@RequestParam("id") String id,@RequestParam("employeeCode") String employeeCode) throws TodoException{
        try{
            todoService.deleteToDo(id, employeeCode);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }
        catch (TodoException todoException) {
            return new ResponseEntity<>(todoException.getMessage(), HttpStatusCode.valueOf(401));
        }
    }

    @PostMapping("/setStatus")
    public ResponseEntity<String> setStatus(@RequestParam("id")String id,@RequestParam("employeeCode") String employeeCode, @RequestParam("status") String status)throws TodoException {
        return new ResponseEntity<String>(todoService.setStatus(id,employeeCode,status),HttpStatus.OK);
    }

    @PostMapping("/setPriority")
    public ResponseEntity<String> setPriority(@RequestParam("employeeCode") String employeeCode, @RequestParam("priority") String priority)throws TodoException {
        return new ResponseEntity<String>(todoService.setPriority(employeeCode,priority),HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test(){
        return "Hello !! THis is taskcenter";
    }
}
