package com.microservices.Broadcasting.Repository;

import com.microservices.Broadcasting.Entity.BroadCasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadCastingRepo extends JpaRepository<BroadCasting, Integer> {

}
