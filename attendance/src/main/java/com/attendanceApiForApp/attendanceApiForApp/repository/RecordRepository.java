package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Records, Long> {
//    @Query("SELECT r FROM Records r WHERE MONTH(r.date) = :month")
//    List<Records> findByMonth(int month);

//    @Query("SELECT r FROM Records r WHERE r.employee.employee_id = :empId AND MONTH(r.date) = :month")
//    List<Records> findByEmployeeAndMonth(Long empId, int month);
}
