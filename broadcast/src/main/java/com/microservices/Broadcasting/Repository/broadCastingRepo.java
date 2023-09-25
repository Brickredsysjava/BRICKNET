package com.microservices.Broadcasting.Repository;

import com.microservices.Broadcasting.Entity.broadCasting;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface broadCastingRepo extends JpaRepository<broadCasting, Integer> {
}
