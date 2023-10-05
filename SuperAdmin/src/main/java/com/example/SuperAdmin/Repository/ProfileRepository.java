package com.example.SuperAdmin.Repository;


import com.example.SuperAdmin.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends JpaRepository<Profile,String> {
    Profile findByEmployeeCode(String employeeCode);


}
