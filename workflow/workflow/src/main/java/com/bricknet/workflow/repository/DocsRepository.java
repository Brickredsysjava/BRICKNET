package com.bricknet.workflow.repository;

import com.bricknet.workflow.model.Docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocsRepository extends JpaRepository<Docs,Long> {
}
