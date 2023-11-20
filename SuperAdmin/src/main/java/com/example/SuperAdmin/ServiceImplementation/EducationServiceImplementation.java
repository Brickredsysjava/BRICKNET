package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.DTO.EducationDTO;
import com.example.SuperAdmin.Entity.Education;
import com.example.SuperAdmin.Repository.CustomQuery;
import com.example.SuperAdmin.Repository.EducationRepository;
import com.example.SuperAdmin.Service.EducationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImplementation implements EducationService {

    @Autowired
    private EducationRepository educationRepository;

    private CustomQuery customQuery;
    @Autowired
    private ModelMapper modelMapper;

    public EducationServiceImplementation(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }

    @Override
    public Education saveEducation(Education education) {

        return educationRepository.save(education);
    }
    @Override
    public List<Education> saveEducationList(List<Education> educations) {
        return educationRepository.saveAll(educations);
    }
    @Override
    public List<EducationDTO> getEducation() {
        List<Education> educationList = educationRepository.findAll();
        return educationList.stream()
                .map(education -> modelMapper.map(education, EducationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EducationDTO> getEducationByEmployeeCode(String employeeCode) {
        List<EducationDTO> educationDTO = (List<EducationDTO>) customQuery.getEducationDetailByEmployeeCode(employeeCode);
        return educationDTO;
    }

    @Override
    public String deleteEducation(String id) {
        educationRepository.deleteById(id);
        return "Deleted";
    }

@Override
public Education updateEducationById(String id, Education education) {

    Education existingEducation = educationRepository.findById(id).orElse(null);

    if (existingEducation != null) {

        existingEducation.setTypeOfEducation(education.getTypeOfEducation());
        existingEducation.setPassingYear(education.getPassingYear());
        existingEducation.setPercentage(education.getPercentage());
        existingEducation.setBoard(education.getBoard());
        existingEducation.setInstituteName(education.getInstituteName());
        existingEducation.setPlace(education.getPlace());

        return educationRepository.save(existingEducation);
    } else {
        // Handle the case where the Education with the provided ID is not found
        return null;
    }
}

}
