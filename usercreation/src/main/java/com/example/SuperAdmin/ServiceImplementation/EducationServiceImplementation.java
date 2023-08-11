package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.Entity.Education;
import com.example.SuperAdmin.Repository.EducationRepository;
import com.example.SuperAdmin.Service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EducationServiceImplementation implements EducationService {

    @Autowired
    private EducationRepository educationRepository;
@Override
    public Education saveEducation(Education education) {

        return educationRepository.save(education);
    }
    @Override
    public List<Education> saveEducationList(List<Education> educations) {
        return educationRepository.saveAll(educations);
    }
    @Override
    public List<Education> getEducation() {
        return educationRepository.findAll();
    }
    @Override
    public Education getEducationById(Long id) {

        return educationRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteEducation(Long id) {
        educationRepository.deleteById(id);
        return "Education removed !! " + id;
    }
    @Override
    public Education updateEducation(Education education) {
        Education existingEducation = educationRepository.findById(education.getId()).orElse(null);

        return educationRepository.save(existingEducation);
    }
}
