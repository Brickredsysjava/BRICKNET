package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Entity.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);

    List<User> saveUsers(List<User> users);

    List<User> getUser();

    User getUserByEmployeeCode(String employeeCode);


    String deleteUser(String employeeCode);

}
