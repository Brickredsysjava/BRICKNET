package com.microservice.login.Login.Service;


import com.microservice.login.Login.Models.User;
import com.microservice.login.Login.repositories.CustomQueries;
import com.microservice.login.Login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HomeServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomQueries customQueries;
    private List<User> store = new ArrayList<>();

    User store_one = new User(UUID.randomUUID().toString(), "Admin", "admin@gmail.com", "Admin", "This is admin user");
    /*public HomeServices(){
        store.add(new User(UUID.randomUUID().toString(), "Piyush Rai", "piyushrai558@gmail.com"));
        //store.add(new User(UUID.randomUUID().toString(), "Pankaj pandey", "pankaj.pandey@gmail.com"));
        //store.add(new User(UUID.randomUUID().toString(), "Debayan Bhatcharjee", "Debayan.Bhatcharjee@gmail.com"));
    }*/

    public List<User> getUsers()
    {
        return this.userRepository.findAll();
    }

    public User createUser(User user){
        user.setUserID(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }
}
