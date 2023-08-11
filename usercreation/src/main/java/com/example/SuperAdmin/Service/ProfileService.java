package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Entity.Profile;

import java.util.List;

public interface ProfileService {


    Profile saveProfile(Profile profile);

    List<Profile> saveProfile(List<Profile> profiles);

    List<Profile> getProfile();


    Profile getProfileById(Long id);

    //    public User getEmployeeByName(String name) {
//        return userRepository.findByName(name);
//    }
    String deleteProfile(Long id);

    Profile updateProfile(Profile profile);
}
