package com.example.SuperAdmin.ServiceImplementation;
import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.Entity.User;
import com.example.SuperAdmin.Repository.UserRepository;
import com.example.SuperAdmin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {

        Profile profile = user.getProfile();
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        user.setProfile(profile);
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
    public User getUserById(String id) {

        return userRepository.findById(id).orElse(null);
    }
    @Override
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "Deleted";
    }


}
