package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Entity.PersonalDetails;

import java.util.List;

public interface PersonalDetailsService {
    PersonalDetails savePersonalDetails(PersonalDetails personalDetails);

    List<PersonalDetails> savePersonalDetailsList(List<PersonalDetails> personalDetails);

    List<PersonalDetails> getPersonalDetails();

    PersonalDetails getPersonalDetailsById(Long id);

    //    public User getEmployeeByName(String name) {
//        return userRepository.findByName(name);
//    }
    String deletePersonalDetails(Long id);

    PersonalDetails updatePersonalDetails(PersonalDetails personalDetails);
}
