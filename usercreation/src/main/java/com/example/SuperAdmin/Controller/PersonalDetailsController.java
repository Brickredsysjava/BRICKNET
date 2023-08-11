package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.Entity.PersonalDetails;
import com.example.SuperAdmin.ServiceImplementation.PersonalDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonalDetailsController {
    @Autowired
    private PersonalDetailsServiceImplementation personalDetailsServiceImplementation;

    @PostMapping("/addPersonalDetails")
    public PersonalDetails addPersonalDetails(@RequestBody PersonalDetails personalDetails) {
        return personalDetailsServiceImplementation.savePersonalDetails(personalDetails);
    }

    @PostMapping("/addListOfPersonalDetails")
    public List<PersonalDetails> addPersonalDetails(@RequestBody List<PersonalDetails> personalDetails) {
        return personalDetailsServiceImplementation.savePersonalDetailsList(personalDetails);
    }

    @GetMapping("/allPersonalDetails")
    public List<PersonalDetails> findAllPersonalDetails() {
        return personalDetailsServiceImplementation.getPersonalDetails();
    }

    @GetMapping("/PersonalDetailsById/{id}")
    public PersonalDetails findPersonalDetailsById(@PathVariable Long id) {
        return personalDetailsServiceImplementation.getPersonalDetailsById(id);
    }

    @PutMapping("/updatePersonalDetails")
    public PersonalDetails updatePersonalDetails(@RequestBody PersonalDetails personalDetails) {
        return personalDetailsServiceImplementation.updatePersonalDetails(personalDetails);
    }

    @DeleteMapping("/deletePersonalDetails/{id}")
    public String deletePersonalDetails(@PathVariable Long id) {
        return personalDetailsServiceImplementation.deletePersonalDetails(id);
    }
}
