package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.EducationDTO;
import com.example.SuperAdmin.Entity.Education;
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
@RequestMapping("/user")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @Autowired
    private ModelMapper modelMapper;

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

    @GetMapping("/EducationById/{id}")
    public ResponseEntity<EducationDTO> findEducationById(@PathVariable String id) {
        EducationDTO education = educationService.getEducationById(id);
        if (education != null) {
            return new ResponseEntity<>(education, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateEducation/{id}")
    public ResponseEntity<Education> updateEducationById(@PathVariable String id, @RequestBody @Valid EducationDTO educationDTO) {
        Education education = modelMapper.map(educationDTO, Education.class);
        Education updatedEducation = educationService.updateEducationById(id, education);
        if (updatedEducation != null) {
            return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEducation/{id}")
    public ResponseEntity<String> deleteEducation(@PathVariable String id) {
        String result = educationService.deleteEducation(id);
        if ("Deleted".equals(result)) {
            return new ResponseEntity<>("Education with ID " + id + " has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Education with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
