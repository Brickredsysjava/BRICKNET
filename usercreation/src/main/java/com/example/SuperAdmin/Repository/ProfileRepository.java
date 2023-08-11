package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Entity.Address;
import com.example.SuperAdmin.Entity.Education;
import com.example.SuperAdmin.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {


}
