package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.DTO.PersonalDetailsDTO;
import com.example.SuperAdmin.Entity.PersonalDetails;
import com.example.SuperAdmin.Entity.Profile;
import com.example.SuperAdmin.Repository.CustomQuery;
import com.example.SuperAdmin.Repository.PersonalDetailsRepository;
import com.example.SuperAdmin.Repository.ProfileRepository;
import com.example.SuperAdmin.Service.PersonalDetailsService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonalDetailsServiceImplementation implements PersonalDetailsService {

    private CustomQuery customQuery;
    public PersonalDetailsServiceImplementation(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    @Transactional
    public PersonalDetails savePersonalDetails(PersonalDetails personalDetails) {

       return personalDetailsRepository.save(personalDetails);
    }
    @Override
    public List<PersonalDetails> savePersonalDetailsList(List<PersonalDetails> personalDetails) {
        return personalDetailsRepository.saveAll(personalDetails);
    }
    @Override
    public List<PersonalDetailsDTO> getPersonalDetails() {
        List<PersonalDetails> personalDetailsList = personalDetailsRepository.findAll();
        return personalDetailsList.stream()
                .map(personalDetails -> modelMapper.map(personalDetails, PersonalDetailsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonalDetailsDTO getPersonalDetailsByEmployeeCode(String employeeCode) {
        return customQuery.getPersonalDetailsByEmployeeCode(employeeCode);
    }

@Override
public PersonalDetails updatePersonalDetailsById(String id, PersonalDetails personalDetails) {
    PersonalDetails existingPersonalDetails = personalDetailsRepository.findById(id).orElse(null);

    if (existingPersonalDetails != null) {
        existingPersonalDetails.setDateOfBirth(personalDetails.getDateOfBirth());
        existingPersonalDetails.setNationality(personalDetails.getNationality());
        existingPersonalDetails.setFatherName(personalDetails.getFatherName());
        existingPersonalDetails.setReligion(personalDetails.getReligion());
        existingPersonalDetails.setMarital(personalDetails.getMarital());
        existingPersonalDetails.setInternationalEmployee(personalDetails.getInternationalEmployee());
        existingPersonalDetails.setPhysicallyChallenged(personalDetails.getPhysicallyChallenged());
        existingPersonalDetails.setPlaceOfBirth(personalDetails.getPlaceOfBirth());

        return personalDetailsRepository.save(existingPersonalDetails);
    } else {
        return null;
    }
}

}
