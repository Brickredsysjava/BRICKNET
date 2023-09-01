package com.usercreation.UserCreation.Controllers;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.usercreation.UserCreation.Model.User;
import com.usercreation.UserCreation.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println("This is the user data - ---- ");
        System.out.println(user);
        User savedUSer = this.userRepository.save(user);
        return new ResponseEntity<User>(savedUSer, HttpStatus.CREATED);
    }
}
