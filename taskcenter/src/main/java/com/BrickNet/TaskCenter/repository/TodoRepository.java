package com.BrickNet.TaskCenter.repository;

import com.BrickNet.TaskCenter.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {



    @Query("SELECT t FROM Todo t WHERE t.id = ?1")
    Todo findByStringId(String id);

    @Query("SELECT t FROM Todo t WHERE t.employeeAssignedBy = ?1")
    Todo findByStringEmployeeAssignedBy(String employeeAssignedBy);

    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE t.id = ?1")
    void deleteByStringId(String id);
}
