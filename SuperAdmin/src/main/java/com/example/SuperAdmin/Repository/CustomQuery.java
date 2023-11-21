package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.DTO.EducationDTO;
import com.example.SuperAdmin.DTO.PersonalDetailsDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;


import java.util.List;


@Configuration
public class CustomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<EducationDTO> getEducationDetailByEmployeeCode(String employee_code) {
        String query = "select * from education where user_id= " +
                "(select user_id from user where profile_id= " +
                "(select id from profile where employee_code= :employee_code))";

        Query q = entityManager.createNativeQuery(query);
        q.setParameter("employee_code",employee_code);
        List<EducationDTO> educationDTO = (List<EducationDTO>) q.getResultList();
        return educationDTO;

    }

    @Transactional
    public PersonalDetailsDTO getPersonalDetailsByEmployeeCode(String employee_code) {
        String query = "select * from personal_details where id= " +
                "(select personal_details_id from user where profile_id= " +
                "(select id from profile where employee_code= :employee_code))";

        PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO();


        Query q = entityManager.createNativeQuery(query);
        q.setParameter("employee_code",employee_code);


       Object result = q.getSingleResult();
       Object[] row = (Object[]) result;


       personalDetailsDTO.setDateOfBirth((String) row[1]);
       personalDetailsDTO.setFatherName((String) row[2]);
       personalDetailsDTO.setInternationalEmployee((String) row[3]);
       personalDetailsDTO.setMaritalStatus((String) row[4]);
       personalDetailsDTO.setNationality((String) row[5]);
       personalDetailsDTO.setPhysicallyChallenged((String) row[6]);
       personalDetailsDTO.setPlaceOfBirth((String) row[7]);
       personalDetailsDTO.setReligion((String) row[8]);

       return personalDetailsDTO;

    }

}
