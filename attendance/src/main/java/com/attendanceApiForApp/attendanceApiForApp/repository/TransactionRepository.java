package com.attendanceApiForApp.attendanceApiForApp.repository;

import com.attendanceApiForApp.attendanceApiForApp.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, String> {

}
