package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.UserDTO;
import com.example.SuperAdmin.Entity.*;
import com.example.SuperAdmin.ServiceImplementation.UserServiceImplementation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/user/creation")
public class UserController {
    @Autowired
   private ModelMapper modelMapper;
    @Autowired
    private UserServiceImplementation service;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO,User.class);
        User savedUser = service.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @PostMapping("/addListOfUser")
    public ResponseEntity<List<User>> addUserList(@Valid @RequestBody List<UserDTO> userDTOList) {
    List<User> users = userDTOList.stream()
            .map(userDTO -> modelMapper.map(userDTO, User.class))
            .collect(Collectors.toList());
    List<User> savedUsers = service.saveUsers(users);
    return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
}

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> findAllUser() {
        List<User> allUsers = service.getUser();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/UserById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {

        User user = service.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Program run successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        String result = service.deleteUser(id);
        if ("Deleted".equals(result)) {
            return new ResponseEntity<>("User with ID " + id + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}


