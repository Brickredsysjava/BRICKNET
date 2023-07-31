package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn,Long> {

@Query("SELECT c.checkinTime FROM CheckIn c order by c.checkinTime LIMIT 1")
String findFirstCheckInTime();
}
