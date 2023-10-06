package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.PersonalDetailsDTO;
import com.example.SuperAdmin.Entity.PersonalDetails;
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

    @GetMapping("/PersonalDetailsById/{id}")
    public ResponseEntity<PersonalDetailsDTO> findPersonalDetailsById(@PathVariable String id) {
        PersonalDetailsDTO personalDetails = personalDetailsService.getPersonalDetailsById(id);
        if (personalDetails != null) {
            return new ResponseEntity<>(personalDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatePersonalDetails/{id}")
    public ResponseEntity<PersonalDetails> updatePersonalDetailsById(@PathVariable String id, @RequestBody @Valid PersonalDetailsDTO personalDetailsDTO) {
        PersonalDetails personalDetails = modelMapper.map(personalDetailsDTO, PersonalDetails.class);
        PersonalDetails updatedDetails = personalDetailsService.updatePersonalDetailsById(id, personalDetails);
        if (updatedDetails != null) {
            return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
