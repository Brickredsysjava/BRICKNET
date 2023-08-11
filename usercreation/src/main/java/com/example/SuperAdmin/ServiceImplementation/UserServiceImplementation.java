package com.example.SuperAdmin.ServiceImplementation;
import com.example.SuperAdmin.Entity.User;
import com.example.SuperAdmin.Repository.UserRepository;
import com.example.SuperAdmin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }
    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User removed !! " + id;
    }

@Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);
        return userRepository.save(existingUser);
    }
}
