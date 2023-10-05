package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.EducationDTO;
import com.example.SuperAdmin.Entity.Education;

import java.util.List;
import java.util.UUID;

public interface EducationService {

    Education saveEducation(Education education);

    List<Education> saveEducationList(List<Education> educations);

    List<EducationDTO> getEducation();

    EducationDTO getEducationById(String id);

    String deleteEducation(String id);

    Education updateEducationById(String id,Education education);
}
