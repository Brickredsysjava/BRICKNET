package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}
