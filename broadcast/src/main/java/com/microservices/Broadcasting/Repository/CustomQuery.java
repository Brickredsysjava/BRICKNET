package com.microservices.Broadcasting.Repository;

import com.microservices.Broadcasting.Dto.BroadCastingDTO;
import com.microservices.Broadcasting.Dto.GetBroadcastInfoDTO;
import com.microservices.Broadcasting.Entity.broadCasting;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<GetBroadcastInfoDTO> getAllBroadCast(){
        try{
            String query = "select message, start_time, end_time, type_of_event, file_name from broad_casting order by selected_date asc;";
            Query q = entityManager.createNativeQuery(query);
//        List<GetBroadcastInfoDTO> res = q.getResultList();

            List<GetBroadcastInfoDTO> getBroadcastInfoDTOList = new ArrayList<>();

            List<Object> objects = q.getResultList();

            for (Object o : objects) {
                Object[] row = (Object[]) o;
                GetBroadcastInfoDTO getBroadcastInfoDTO = new GetBroadcastInfoDTO();

                getBroadcastInfoDTO.setMessage((String) row[0]);
                getBroadcastInfoDTO.setStart_time(((Timestamp) row[1]).toLocalDateTime());
                getBroadcastInfoDTO.setEnd_time(((Timestamp) row[2]).toLocalDateTime());
                getBroadcastInfoDTO.setType_of_event((String) row[3]);
                getBroadcastInfoDTO.setFilename((String) row[4]);

                getBroadcastInfoDTOList.add(getBroadcastInfoDTO);
            }
            return getBroadcastInfoDTOList;
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<GetBroadcastInfoDTO> getNewsletter(){
        try{
        String query = "select message, start_time, end_time, type_of_event, file_name from broad_casting where type_of_even =:newsletter order by selected_date asc";
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("newsletter","Newsletter");
        List<GetBroadcastInfoDTO> getBroadcastInfoDTOList = new ArrayList<>();

        List<Object> objects = q5.getResultList();

        for (Object o : objects) {
            Object[] row = (Object[]) o;
            GetBroadcastInfoDTO getBroadcastInfoDTO = new GetBroadcastInfoDTO();

            getBroadcastInfoDTO.setMessage((String) row[0]);
            getBroadcastInfoDTO.setStart_time(((Timestamp) row[1]).toLocalDateTime());
            getBroadcastInfoDTO.setEnd_time(((Timestamp) row[2]).toLocalDateTime());
            getBroadcastInfoDTO.setType_of_event((String) row[3]);
            getBroadcastInfoDTO.setFilename((String) row[4]);

            getBroadcastInfoDTOList.add(getBroadcastInfoDTO);
        }
        return getBroadcastInfoDTOList;
    }
    catch (Exception e) {
        e.getMessage();
    }
    return null;
    }
}
