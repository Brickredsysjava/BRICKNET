package com.example.SuperAdmin.Controller;
import com.example.SuperAdmin.Entity.User;
import com.example.SuperAdmin.ServiceImplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImplementation service;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/addListOfUser")
    public List<User> addUser(@RequestBody List<User> users) {
        return service.saveUsers(users);
    }

    @GetMapping("/allUser")
    public List<User> findAllUser() {
        return service.getUser();
    }

    @GetMapping("/UserById/{id}")
    public User findUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

//    @PutMapping("/updateUser")
//    public User updateUser(@RequestBody User user) {
//        return service.updateUser(user);
//    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }
}

