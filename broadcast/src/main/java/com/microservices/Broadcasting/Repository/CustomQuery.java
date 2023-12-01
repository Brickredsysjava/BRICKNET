package com.microservices.Broadcasting.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> getAllBroadCast(){
        String query = "select message from broad_casting order by selected_date asc;";
        Query q = entityManager.createNativeQuery(query);
        List<String> res = q.getResultList();
        return res;
    }
}
