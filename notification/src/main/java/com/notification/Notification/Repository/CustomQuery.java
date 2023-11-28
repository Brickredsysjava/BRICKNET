package com.notification.Notification.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> getAllNotificationByEmployeeCode(String empCode){
        String query = "select message from notification " +
                "where recipient = (select company_email from profile where employee_code= :empCode)" +
                "order by timestamp asc ";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("empCode", empCode);
        List<String> res = q.getResultList();
        return res;
    }

}
