package com.microservices.Broadcasting.Repository;

import com.microservices.Broadcasting.Dto.GetBroadcastInfoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<GetBroadcastInfoDTO> getAllBroadCast(){
        String query = "select message, start_time, end_time, type_of_event from broad_casting order by selected_date asc;";
        Query q = entityManager.createNativeQuery(query);
        List<GetBroadcastInfoDTO> res = q.getResultList();
        return res;
    }
}
