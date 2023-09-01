package com.microservice.login.Login.Controller;

import com.microservice.login.Login.Models.User;
import com.microservice.login.Login.Service.HomeServices;
import com.microservice.login.Login.repositories.CustomQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController
{

    @Autowired
    private HomeServices homeServices;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getProtectedApi(){
        return new ResponseEntity<List<User>>(this.homeServices.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/current")
    public String getCurrentUser(Principal principal){
        return principal.getName();
    }

}
