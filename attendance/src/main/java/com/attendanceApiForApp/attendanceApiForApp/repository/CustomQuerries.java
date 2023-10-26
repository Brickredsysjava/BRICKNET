package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.dto.HistoryDto;
import com.attendanceApiForApp.attendanceApiForApp.dto.TimeSheetDto;
import com.attendanceApiForApp.attendanceApiForApp.dto.TransactionDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

@Configuration
public class CustomQuerries {
    @PersistenceContext
    private EntityManager entityManager;

    //Custome Query for User Login on the basis of emp_id and password
    @Transactional
    public String loginApi(String emp_id, String password ){
        String query = " select auto_id from  employee where emp_id = :emp_id and password = :password";
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("emp_id",emp_id);
        q5.setParameter("password",password);
        String result = q5.getSingleResult().toString();
        return result;
    }

    //Custome query to fetch last record_id on the basis of emp_id
    public Object findLastRecorId(String emp_id) {

        String query = " select max(record_id) from record where employee_id= ?1";
        Query q = entityManager.createNativeQuery(query);

        q.setParameter(1, emp_id);
        Object result = q.getSingleResult().toString();
        return result;
    }

    //Custom query to update duration of record entity on the basis of emp_id,record_id
    public  void postDurationToRecord(String emp_id, String record_id , String durations ){
        String query = "  UPDATE record  SET durations = :durations  WHERE record_id = :record_id AND employee_id = :emp_id";
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("emp_id",emp_id);
        q5.setParameter("record_id",record_id);
        q5.setParameter("durations",durations);
        q5.executeUpdate();
    }

    //Custome query to fetch history on the basis of empi_id ,record_id and month
    public  List<HistoryDto> getHistoryData(int months, String auto_id){
         String query = " Select DATE(r.date),min(t.checkin_times),max(checkout_times),\n" +
                 " r.durations,r.timezone from transactions t \n" +
                 " join record r on t.record_id = r.record_id \n" +
                 " where r.auto_id =:auto_id and \n" +
                 " MONTH (date) = :months \n" +
                 " group by r.record_id  ORDER BY r.date ASC ";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("months", months);
        q.setParameter("auto_id", auto_id);
        List<HistoryDto> result = q.getResultList();
        return result;
    }

     //Custome query for current date data fetching on the basis of emp_id and record_id
    public  List<TransactionDto> getCurrentDetails(String record_id){
        String query = " select checkin_times ,checkout_times,durations from transactions " +
                "   where record_id = :record_id  ORDER BY checkout_times DESC ";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("record_id", record_id);
        List<TransactionDto> result = q.getResultList();
        return  result;
    }

    //Custome query to fetch last transaction id on the basis of record_id and emp_id
    @Transactional
    public String getLastTransactionId(String record_id, String emp_id){
        String query = "SELECT max(t.transaction_id)" +
                "FROM Transactions t" +
                "JOIN record r ON t.record_id = r.record_id" +
                " And r.employee_id = r.employee_id" +
                "WHERE r.employee_id = :emp_id"  +
                " AND r.record_id = :record_id ";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("emp_id", emp_id);
        q.setParameter("record_id", record_id);
        String result = (String) q.getSingleResult();
        return  result;
    }

    //Custom query to update checkout_times on the basis of transaction_id
    @Transactional
    public void updateCheckout(String checkout_times,String transaction_id ){
        String query = "  UPDATE transactions set checkout_times = :checkout_times where transaction_id =:transaction_id " ;
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("checkout_times",checkout_times);
        q5.setParameter("transaction_id",transaction_id );
        q5.executeUpdate();
    }

    //Custom query to update duration  of each _transactions on the basis of transaction_id
    @Transactional
    public  void updateDurationInTransaction(String durations, String transaction_id ){
        String query = " UPDATE transactions set durations = :durations  where transaction_id = :transaction_id" ;
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("durations",durations);
        q5.setParameter("transaction_id",transaction_id );
        q5.executeUpdate();
    }

    @Transactional
    public List<Object> getListOfTransactionId(String employeeID){
        String query = "select * from transactions t join record r on t.record_id = r.record_id where r.employee_id =:emp_id ";
        Query q6 = entityManager.createNativeQuery(query);
        q6.setParameter("emp_id", employeeID);
        List<Object> result = q6.getResultList();
        return result;
    }

    public String getRecordId(String auto_id, Date todayDate){
            String query = "select record_id from record \n" +
                    " where auto_id = :auto_id and Date(date) = :todayDate";
            Query q6 = entityManager.createNativeQuery(query);
            q6.setParameter("auto_id", auto_id);
            q6.setParameter("todayDate", todayDate);
            String result = q6.getSingleResult().toString();
            return result;

    }

    //custome query to update duration(total durations sum) in record table

    @Transactional
    public  void updateDurationInRecord(String durations, String record_id ){
        String query = "  UPDATE record SET durations = :durations where record_id = :record_id" ;
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("durations",durations);
        q5.setParameter("record_id",record_id );
        q5.executeUpdate();
    }

    //custome query to update status in record table
    @Transactional
    public  void updateStatusInRecord(boolean status, String record_id ){
        String query = " UPDATE record SET status = :status where record_id = :record_id" ;
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("status",status);
        q5.setParameter("record_id",record_id );
        q5.executeUpdate();
    }
    //custome query to update TimeZone in record table
    @Transactional
    public  void updateTimeZoneInRecord(String record_id, String timezone ){
        String query = " UPDATE record SET timezone = :timezone where record_id = :record_id" ;
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("record_id",record_id );
        q5.setParameter("timezone",timezone);
        q5.executeUpdate();
    }
  // custome query to get the status value
    public boolean getStatusValue(String record_id ){
        String query = "select status from record where record_id = :record_id";
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("record_id",record_id );
        boolean result = (boolean) q5.getSingleResult();
        return result;
    }

    public boolean getIsSelecteTimeZone(String record_id ){
        String query = "select time_zone_selected from record where record_id = :record_id";
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("record_id",record_id );
        boolean result = (boolean) q5.getSingleResult();
        return result;
    }

    @Transactional
    public  void updateIsSelectTimeZoneInRecord(boolean time_zone_selected, String record_id ){
        String query = " UPDATE record SET time_zone_selected = :time_zone_selected where record_id = :record_id" ;
        Query q5 = entityManager.createNativeQuery(query);
        q5.setParameter("time_zone_selected",time_zone_selected);
        q5.setParameter("record_id",record_id );
        q5.executeUpdate();
    }
    @Transactional
    public String getTimeZone(String record_id) {
        String query = "select timezone from record where record_id = :record_id";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("record_id", record_id);
        String result = (String) q.getSingleResult();
        return  result;
    }


}
