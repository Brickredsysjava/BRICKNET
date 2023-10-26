package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
