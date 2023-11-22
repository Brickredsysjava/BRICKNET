package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.ProfileDTO;
import com.example.SuperAdmin.DTO.ResetPassword;
import com.example.SuperAdmin.auth.UserCredential;
import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.DTO.TimeLine;

import java.util.List;

public interface ProfileService {

    TimeLine getTimelineByEmployeeCode(String employeeCode);

    Profile saveProfile(Profile profile);

    Profile updateProfileById(String id, Profile profile);

    List<Profile> saveProfile(List<Profile> profiles);

    List<ProfileDTO> getProfile();

    ProfileDTO getProfileByEmployeeCode(String employeeCode);

    String getFullNameByEmployeeCode(String employeeCode);

    UserCredential getByUserName(String username);

    UserCredential updatePassword(ResetPassword resetPassword);

    String getEmailByEmployeeCode(String empcode);

    List<String> getAllEmails();

}
