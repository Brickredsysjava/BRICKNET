package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.DTO.EducationDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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

}
