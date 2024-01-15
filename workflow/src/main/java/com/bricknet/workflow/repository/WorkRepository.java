package com.bricknet.workflow.repository;

import com.bricknet.workflow.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository  extends JpaRepository<Work, String> {
}
