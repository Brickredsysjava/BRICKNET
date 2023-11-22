package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.DTO.ProfileDTO;
import com.example.SuperAdmin.DTO.ResetPassword;
import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.DTO.TimeLine;
import com.example.SuperAdmin.Repository.PersonalDetailsRepository;
import com.example.SuperAdmin.Repository.ProfileRepository;
import com.example.SuperAdmin.Service.ProfileService;
import com.example.SuperAdmin.auth.UserCredential;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImplementation implements ProfileService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;



    @Override
    public Profile saveProfile(Profile profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profile.setEmployeeCode(profile.getEmployeeCode().toUpperCase());
        return profileRepository.save(profile);
    }
    @Override
    public List<Profile> saveProfile(List<Profile> profiles) {

        return profileRepository.saveAll(profiles);
    }
    @Override
    public List<ProfileDTO> getProfile() {
        List<Profile> profileList = profileRepository.findAll();
        return profileList.stream()
                .map(profile -> modelMapper.map(profile, ProfileDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDTO getProfileByEmployeeCode(String employeeCode) {
        Profile profile = profileRepository.findByEmployeeCode(employeeCode);
        return modelMapper.map(profile,ProfileDTO.class);
    }

    @Override
    public TimeLine getTimelineByEmployeeCode(String employeeCode) {
        Profile profile = profileRepository.findByEmployeeCode(employeeCode);
        if (profile != null) {
            TimeLine timeLine = new TimeLine();
            timeLine.setDesignation(profile.getDesignation());
            timeLine.setReportingTo(profile.getReportingTo());
            timeLine.setDepartment(profile.getDepartment());
            timeLine.setLocation(profile.getLocation());
            return timeLine;
        } else {
            return null; // Or throw an exception based on your requirements
        }
    }


    @Override
public Profile updateProfileById(String id, Profile profile) {

    Profile existingProfile = profileRepository.findById(id).orElse(null);

    if (existingProfile != null) {
        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setDepartment(profile.getDepartment());
        existingProfile.setDesignation(profile.getDesignation());
        existingProfile.setLocation(profile.getLocation());
        existingProfile.setBloodGroup(profile.getBloodGroup());
        existingProfile.setCompanyEmail(profile.getCompanyEmail());
        existingProfile.setEmployeeCode(profile.getEmployeeCode());
        existingProfile.setGender(profile.getGender());
        existingProfile.setPassword(profile.getPassword());
        existingProfile.setPersonalEmail(profile.getPersonalEmail());
        existingProfile.setPhoneNumber(profile.getPhoneNumber());
        existingProfile.setReportingTo(profile.getReportingTo());
        existingProfile.setRole(profile.getRole());
        return profileRepository.save(existingProfile);
    } else {
        return null;
    }
}
    @Override
    public String getFullNameByEmployeeCode(String employeeCode) {
        Profile profile = profileRepository.findByEmployeeCode(employeeCode);
        if (profile != null) {
            return profile.getFirstName() + " " + profile.getLastName();
        } else {
            return null;
        }
    }

    @Override
    public UserCredential getByUserName(String userName) {
        List<Profile> userProfileList = profileRepository.findAll();

        for (Profile profile : userProfileList) {
            if (profile.getEmployeeCode().equals(userName) || profile.getCompanyEmail().equals(userName) || profile.getPhoneNumber().equals(userName)) {
                UserCredential userCredential = UserCredential.builder()
                        .uuid(profile.getId())
                                .employeeName(profile.getFirstName() + " " + profile.getLastName())
                                        .employeeCode(profile.getEmployeeCode())
                                                .companyEmail(profile.getCompanyEmail())
                                                        .role(String.valueOf(profile.getRole()))
                                                                .password(profile.getPassword()).build();
                return userCredential;
            }
        }

        return null;
    }

    @Override
    public UserCredential updatePassword(@RequestBody ResetPassword resetPassword) {
        Profile existingProfile=profileRepository.findByEmployeeCode(resetPassword.getUsername());
        if(resetPassword.getUsername().equals(existingProfile.getEmployeeCode()))
        existingProfile.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
        System.out.println(resetPassword.getPassword());
        profileRepository.save(existingProfile);
        UserCredential userCredential = modelMapper.map(existingProfile, UserCredential.class);
        return userCredential;
    }

    @Override
    public String getEmailByEmployeeCode(String empcode) {
        Profile profile = profileRepository.findByEmployeeCode(empcode);
        if(profile != null){
            return profile.getPersonalEmail();
        }
        else {
            return null;
        }

    }
    @Override
    public List<String> getAllEmails() {
        return profileRepository.findByCompanyEmail();
    }


}

