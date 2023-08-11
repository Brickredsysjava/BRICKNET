package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.Entity.PersonalDetails;
import com.example.SuperAdmin.Repository.PersonalDetailsRepository;
import com.example.SuperAdmin.Service.PersonalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalDetailsServiceImplementation implements PersonalDetailsService {
    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;
    @Override
    public PersonalDetails savePersonalDetails(PersonalDetails personalDetails) {

        return personalDetailsRepository.save(personalDetails);
    }
    @Override
    public List<PersonalDetails> savePersonalDetailsList(List<PersonalDetails> personalDetails) {
        return personalDetailsRepository.saveAll(personalDetails);
    }
    @Override
    public List<PersonalDetails> getPersonalDetails() {
        return personalDetailsRepository.findAll();
    }
    @Override
    public PersonalDetails getPersonalDetailsById(Long id) {

        return personalDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public String deletePersonalDetails(Long  id) {
        personalDetailsRepository.deleteById(id);
        return "PersonalDetails removed !! " + id;
    }
    @Override
    public PersonalDetails updatePersonalDetails(PersonalDetails personalDetails) {
        PersonalDetails existingPersonalDetails = personalDetailsRepository.findById(personalDetails.getId()).orElse(null);

        return personalDetailsRepository.save(existingPersonalDetails);
    }
}
