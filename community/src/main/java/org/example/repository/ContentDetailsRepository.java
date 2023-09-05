package org.example.repository;

import org.example.model.ContentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDetailsRepository extends JpaRepository<ContentDetails,String> {
}
