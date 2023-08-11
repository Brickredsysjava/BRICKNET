package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Entity.BankDetails;
import com.example.SuperAdmin.Entity.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails,Long> {

}
