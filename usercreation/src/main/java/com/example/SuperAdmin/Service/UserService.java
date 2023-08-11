package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Entity.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);

    List<User> saveUsers(List<User> users);

    List<User> getUser();

    User getUserById(Long id);

    //    public User getEmployeeByName(String name) {
//        return userRepository.findByName(name);
//}
    String deleteUser(Long id);

    User updateUser(User user);
}
