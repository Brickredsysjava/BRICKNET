package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.EducationDTO;
import com.example.SuperAdmin.Entity.Education;
import com.example.SuperAdmin.Repository.CustomQuery;
import com.example.SuperAdmin.Service.EducationService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@Validated
@RequestMapping("/user/education")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @Autowired
    private ModelMapper modelMapper;

    private CustomQuery customQuery;

    public EducationController(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }
    @PostMapping("/addEducation")
    public ResponseEntity<Education> addEducation(@RequestBody @Valid EducationDTO educationDTO) {
        Education education = modelMapper.map(educationDTO, Education.class);
        Education savedEducation = educationService.saveEducation(education);
        return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
    }

    @PostMapping("/addListOfEducation")
    public ResponseEntity<List<Education>> addEducation(@RequestBody @Valid List<EducationDTO> educationDTOList) {
        List<Education> educations = educationDTOList.stream()
                .map(dto -> modelMapper.map(dto, Education.class))
                .collect(Collectors.toList());

        List<Education> savedEducations = educationService.saveEducationList(educations);
        return new ResponseEntity<>(savedEducations, HttpStatus.CREATED);
    }

    @GetMapping("/allEducation")
    public ResponseEntity<List<EducationDTO>> findAllEducation() {
        List<EducationDTO> allEducations = educationService.getEducation();
        return new ResponseEntity<>(allEducations, HttpStatus.OK);
    }

    @GetMapping("/EducationByEmployeeCode")
    public ResponseEntity<List<EducationDTO>> findEducationByEmployeeCode(@RequestParam("employeeCode") String employeeCode) {
        List<EducationDTO> education = (List<EducationDTO>) educationService.getEducationByEmployeeCode(employeeCode);
        if (education != null) {
            return new ResponseEntity<>(education, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEducation")
    public ResponseEntity<Education> updateEducationById(@RequestParam("employeeCode") String employeeCode, @RequestParam("type_Of_Education") String type_of_education, @RequestBody @Valid EducationDTO educationDTO) {
        Education education = modelMapper.map(educationDTO, Education.class);
        String education_id = customQuery.getEducationIdByEmployeeCode(employeeCode, type_of_education);
        Education updatedEducation = educationService.updateEducationById(education_id, education);
        if (updatedEducation != null) {
            return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEducation")
    public ResponseEntity<String> deleteEducation(@RequestParam("employeeCode") String employeeCode, @RequestParam("typeOfEducation") String typeOfEducation) {
        String result = educationService.deleteEducation(employeeCode,typeOfEducation);
        if ("Deleted".equals(result)) {
            return new ResponseEntity<>("Education with employeeCode " + employeeCode + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Education with employeeCode " + employeeCode + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
