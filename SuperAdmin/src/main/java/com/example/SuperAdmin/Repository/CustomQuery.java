package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.DTO.EducationDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public EducationDTO getEducationDetailByEmployeeCode(String employeeCode) {
        String query = "select * from education where user_id= " +
                "(select user_id from user where profile_id= " +
                "(select id from profile where employee_code= :employeeCode))";

        Query q = entityManager.createNativeQuery(query);
        q.setParameter("employeeCode",employeeCode);
        EducationDTO educationDTO = (EducationDTO) q.getSingleResult();
        return educationDTO;

    }

}
