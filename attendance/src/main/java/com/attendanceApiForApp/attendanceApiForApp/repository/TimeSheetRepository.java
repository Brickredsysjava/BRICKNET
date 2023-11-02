package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet,String> {

    TimeSheet findByDates(LocalDate date);
//
//    // Custom query to check if a TimeSheet exists for a given date
//    @Query("SELECT COUNT(t) > 0 FROM TimeSheet t WHERE t.dates = :dates")
//    boolean existsByDate(@Param("dates") LocalDate dates);
//
//    // Custom query to update a TimeSheet
//    @Modifying
//    @Query("UPDATE TimeSheet t SET t.someField = :someField WHERE t.dates = :dates")
//    int updateSomeFieldByDate(@Param("someField") String someField, @Param("dates") LocalDate dates);
}
