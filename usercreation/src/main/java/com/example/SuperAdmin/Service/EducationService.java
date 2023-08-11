package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Entity.Education;

import java.util.List;

public interface EducationService {

    Education saveEducation(Education education);

    List<Education> saveEducationList(List<Education> educations);

    List<Education> getEducation();

    Education getEducationById(Long id);

    //    public User getEmployeeByName(String name) {
//        return userRepository.findByName(name);
//    }
    String deleteEducation(Long id);

    Education updateEducation(Education education);
}
