package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.PersonalDetailsDTO;
import com.example.SuperAdmin.Entity.PersonalDetails;

import java.util.List;
import java.util.UUID;

public interface PersonalDetailsService {
    PersonalDetails savePersonalDetails(PersonalDetails personalDetails);

    List<PersonalDetails> savePersonalDetailsList(List<PersonalDetails> personalDetails);

    List<PersonalDetailsDTO> getPersonalDetails();

    PersonalDetailsDTO getPersonalDetailsByEmployeeCode(String id);

    PersonalDetails updatePersonalDetailsById(String id,PersonalDetails personalDetails);
}
