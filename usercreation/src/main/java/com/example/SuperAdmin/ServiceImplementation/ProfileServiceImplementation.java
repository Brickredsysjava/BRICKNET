package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.Repository.ProfileRepository;
import com.example.SuperAdmin.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class ProfileServiceImplementation implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Profile saveProfile(Profile profile) {

        return profileRepository.save(profile);
    }
    @Override
    public List<Profile> saveProfile(List<Profile> profiles) {
        return profileRepository.saveAll(profiles);
    }
    @Override
    public List<Profile> getProfile() {
        return profileRepository.findAll();
    }
   @Override
    public Profile getProfileById(Long id) {

        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteProfile(Long id) {
        profileRepository.deleteById(id);
        return "Address removed !! " + id;
    }
    @Override
    public Profile updateProfile(Profile profile) {
        Profile existingProfile = profileRepository.findById(profile.getId()).orElse(null);

        return profileRepository.save(existingProfile);
    }
}
