package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.EmployeeDTO;
import com.example.SuperAdmin.DTO.NotificationDTO;
import com.example.SuperAdmin.DTO.ProfileDTO;
import com.example.SuperAdmin.DTO.UserDTO;
import com.example.SuperAdmin.Entity.*;
import com.example.SuperAdmin.Service.EmployeeService;
import com.example.SuperAdmin.Service.NotificationService;
import com.example.SuperAdmin.ServiceImplementation.UserServiceImplementation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/user/creation")
public class UserController {
    @Autowired
   private ModelMapper modelMapper;
    @Autowired
    private UserServiceImplementation service;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDTO userDTO) throws ServiceNotFoundException {
        NotificationDTO notificationDTO = new NotificationDTO();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        User user = this.modelMapper.map(userDTO,User.class);
        ProfileDTO profileDTO = userDTO.getProfileDTO();
        String message =
                "You are created  \n" + "\n"
                        + "UserId:" + profileDTO.getEmployeeCode() + "\n"
                        + "Password: " + profileDTO.getPassword() +"\n" +
                        "\n";
        notificationDTO.setMessage(message);
        notificationDTO.setRecipient(profileDTO.getPersonalEmail());
        notificationDTO.setTimeStamp(LocalDateTime.now());


        User savedUser = service.saveUser(user);
        Profile savedProfile = savedUser.getProfile();
        employeeDTO.setAuto_id(savedProfile.getId());
        employeeDTO.setPassword(profileDTO.getPassword());
        employeeDTO.setName(profileDTO.getFirstName() + " " + profileDTO.getLastName());
        employeeDTO.setEmail(profileDTO.getCompanyEmail());
        employeeDTO.setEmp_id(savedProfile.getEmployeeCode());
        employeeService.insertDataIntoDB(employeeDTO);
        notificationService.pushNotification(notificationDTO);
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

    @GetMapping("/UserByEmployeeCode")
    public ResponseEntity<User> findUserById(@RequestParam("employeeCode") String empployeeCode) {

        User user = service.getUserByEmployeeCode(empployeeCode);
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


