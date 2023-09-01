package com.microservice.login.Login.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomQueries {

    @PersistenceContext
    private EntityManager entityManager;

    public String getPasswordOnTheBasisOfUserName(String username) {
        String query = "SELECT password from users_table where email=:email_id;";

        Query q = entityManager.createNativeQuery(query);
        q.setParameter("email_id" ,username);
        String result = (String) q.getSingleResult();
        return result;
    }

}
