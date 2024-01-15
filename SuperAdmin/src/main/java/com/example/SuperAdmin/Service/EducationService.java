package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.EducationDTO;
import com.example.SuperAdmin.Entity.Education;

import java.util.List;

public interface EducationService {

    Education saveEducation(Education education);

    List<Education> saveEducationList(List<Education> educations);

    List<EducationDTO> getEducation();

    List<EducationDTO> getEducationByEmployeeCode(String id);

    String deleteEducation(String employeeCode, String typeOfEducation);

    Education updateEducationById(String id,Education education);
}
