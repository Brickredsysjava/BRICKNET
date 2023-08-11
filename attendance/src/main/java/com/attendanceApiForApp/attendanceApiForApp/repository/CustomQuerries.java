package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Configuration;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class CustomQuerries {
    @PersistenceContext
    private EntityManager entityManager;

    public String findLasttCheckOutTime(Long emp_id, Date date) {
        String query = "SELECT ci.checkout_time FROM check_outs ci " +
                "INNER JOIN record as r ON ci.record_id = r.record_id  " +
                "INNER JOIN employee as e ON r.employee_id = e.emp_id WHERE e.emp_id = :emp_id " +
                "AND DATE(r.date) = :date ORDER BY ci.checkout_time DESC LIMIT 1";
        Query q = entityManager.createNativeQuery(query);

        q.setParameter("emp_id", emp_id);
        q.setParameter("date", date);

        String result = (String) q.getSingleResult();
        return result;
    }


    public List<String> findFirstCheckInTimes(Long emp_id, List<String> dates) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> formattedDates = new ArrayList<>();

        List<String> firstCheckTimes = new ArrayList<>();

        String convertedDates = dates.stream().map( x-> "'" + x + "'").collect(Collectors.joining(","));

        String query = "SELECT min(checkin_time) FROM check_ins " +
                "where record_id in (" +
                "select r.record_id from record as r where " +
                "employee_id = :emp_id and Date(r.date) in ("+ convertedDates +" ))" +
                "group by record_id;";


        Query q = entityManager.createNativeQuery(query);

        q.setParameter("emp_id", emp_id);

        firstCheckTimes = q.getResultList();


        return firstCheckTimes;
    }



    public List<String> findLastCheckOutTimes(Long emp_id, List<String> dates) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> formattedDates = new ArrayList<>();

        List<String> lastCheckOutTimes = new ArrayList<>();

        String convertedDates = dates.stream().map( x-> "'" + x + "'").collect(Collectors.joining(","));

        String query = "SELECT max(checkout_time) FROM check_outs " +
                "where record_id in (" +
                "select r.record_id from record as r where " +
                "employee_id = :emp_id and Date(r.date) in ("+ convertedDates +" ))" +
                "group by record_id;";


        Query q = entityManager.createNativeQuery(query);

        q.setParameter("emp_id", emp_id);

        lastCheckOutTimes = q.getResultList();


        return lastCheckOutTimes;
    }

    public List<Date> getAllDateOfMonth(Long emp_id, int month) {
        String query1 = "SELECT date FROM record " +
                "WHERE employee_id = :emp_id AND MONTH (date) = :month AND date IS NOT NULL";
        Query q2 = entityManager.createNativeQuery(query1);
        q2.setParameter("emp_id", emp_id);
        q2.setParameter("month", month);

        @SuppressWarnings("unchecked")
        List<Date> res = q2.getResultList();
        return res;
    }

    public Long findLastRecorId(Long emp_id) {
        String query = "SELECT record_id FROM record  where employee_id = :emp_id ORDER BY record_id DESC LIMIT 1";
        Query q = entityManager.createNativeQuery(query);

        q.setParameter("emp_id", emp_id);
         Long result = (Long) q.getSingleResult();
        return result;
    }

}
