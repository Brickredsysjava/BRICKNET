package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut,Long> {
    @Query("SELECT c.checkoutTime FROM CheckOut c order by c.checkoutTime DESC LIMIT 1")
    String findLasttCheckOutTime();
}
