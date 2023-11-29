package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.PersonalDetailsDTO;
import com.example.SuperAdmin.Entity.PersonalDetails;
import com.example.SuperAdmin.Repository.CustomQuery;
import com.example.SuperAdmin.Service.PersonalDetailsService;
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
@RequestMapping("/user/personal")
public class PersonalDetailsController {
    @Autowired
    private PersonalDetailsService personalDetailsService;

    @Autowired
    private ModelMapper modelMapper;


    private CustomQuery customQuery;

    public PersonalDetailsController(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }

    @PostMapping("/addPersonalDetails")
    public ResponseEntity<PersonalDetails> addPersonalDetails(@RequestBody @Valid PersonalDetailsDTO personalDetailsDTO) {
        PersonalDetails personalDetails = modelMapper.map(personalDetailsDTO, PersonalDetails.class);
        PersonalDetails savedDetails = personalDetailsService.savePersonalDetails(personalDetails);
        return new ResponseEntity<>(savedDetails, HttpStatus.CREATED);
    }

    @PostMapping("/addListOfPersonalDetails")
    public ResponseEntity<List<PersonalDetails>> addPersonalDetails(@RequestBody @Valid List<PersonalDetailsDTO> personalDetailsDTOList) {
        List<PersonalDetails> personalDetailsList = personalDetailsDTOList.stream()
                .map(dto -> modelMapper.map(dto, PersonalDetails.class))
                .collect(Collectors.toList());

        List<PersonalDetails> savedDetailsList = personalDetailsService.savePersonalDetailsList(personalDetailsList);
        return new ResponseEntity<>(savedDetailsList, HttpStatus.CREATED);
    }

    @GetMapping("/allPersonalDetails")
    public ResponseEntity<List<PersonalDetailsDTO>> findAllPersonalDetails() {
        List<PersonalDetailsDTO> allPersonalDetails = personalDetailsService.getPersonalDetails();
        if (allPersonalDetails != null) {
            return new ResponseEntity<>(allPersonalDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/PersonalDetailsByEmployeeCode")
    public ResponseEntity<?> findPersonalDetailsById(@RequestParam("employeeCode") String employeeCode) {
        PersonalDetailsDTO personalDetails = personalDetailsService.getPersonalDetailsByEmployeeCode(employeeCode);
        if (personalDetails != null) {
            return new ResponseEntity<>(personalDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Personal Details Not Found",HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updatePersonalDetails")
    public ResponseEntity<PersonalDetails> updatePersonalDetailsById(@RequestParam("employeeCode") String employeeCode, @RequestBody @Valid PersonalDetailsDTO personalDetailsDTO) {
        PersonalDetails personalDetails = modelMapper.map(personalDetailsDTO, PersonalDetails.class);
        String personal_details_id = customQuery.getPersonalDetailsIdByEmpCode(employeeCode);

        if(personal_details_id.equals("Data Not Found")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        PersonalDetails updatedDetails = personalDetailsService.updatePersonalDetailsById(personal_details_id, personalDetails);
        if (updatedDetails != null) {
            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
