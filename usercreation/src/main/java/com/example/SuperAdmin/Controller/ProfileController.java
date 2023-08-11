package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.ServiceImplementation.ProfileServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private ProfileServiceImplementation profileServiceImplementation;

    @PostMapping("/addProfile")
    public Profile addProfile(@RequestBody Profile profile) {
        return profileServiceImplementation.saveProfile(profile);
    }

    @PostMapping("/addListOfProfile")
    public List<Profile> addProfile(@RequestBody List<Profile> profiles) {
        return profileServiceImplementation.saveProfile(profiles);
    }

    @GetMapping("/allProfile")
    public List<Profile> findAllProfile() {
        return profileServiceImplementation.getProfile();
    }

    @GetMapping("/ProfileById/{id}")
    public Profile findProfileById(@PathVariable Long id) {
        return profileServiceImplementation.getProfileById(id);
    }

    @PutMapping("/updateProfile")
    public Profile updateProfile(@RequestBody Profile profile) {
        return profileServiceImplementation.updateProfile(profile);
    }

    @DeleteMapping("/deleteProfile/{id}")
    public String deleteProfile(@PathVariable Long id) {
        return profileServiceImplementation.deleteProfile(id);
    }
}
