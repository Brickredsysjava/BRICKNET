package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.Entity.Education;
import com.example.SuperAdmin.ServiceImplementation.EducationServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EducationController {
    @Autowired
    private EducationServiceImplementation educationServiceImplementation;

    @PostMapping("/addEducation")
    public Education addEducation(@RequestBody Education education) {
            return educationServiceImplementation.saveEducation(education);
    }

    @PostMapping("/addListOfEducation")
    public List<Education> addEducation(@RequestBody List<Education> educations) {
        return educationServiceImplementation.saveEducationList(educations);
    }

    @GetMapping("/allEducation")
    public List<Education> findAllEducation() {
        return educationServiceImplementation.getEducation();
    }

    @GetMapping("/EducationById/{id}")
    public Education findEducationById(@PathVariable Long id) {
        return educationServiceImplementation.getEducationById(id);
    }

    @PutMapping("/updateEducation")
    public Education updateEducation(@RequestBody Education education) {
        return educationServiceImplementation.updateEducation(education);
    }

    @DeleteMapping("/deleteEducation/{id}")
    public String deleteEducation(@PathVariable Long id) {
        return educationServiceImplementation.deleteEducation(id);
    }
}
