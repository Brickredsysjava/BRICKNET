package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface RecordRepository extends JpaRepository<Records, String> {

}
