package com.example.SuperAdmin.Repository;


import com.example.SuperAdmin.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Repository
public interface ProfileRepository extends JpaRepository<Profile,String> {
    Profile findByEmployeeCode(String employeeCode);

    @Query("SELECT p.company_email FROM profile p")
    List<String> findByCompanyEmail();

}
