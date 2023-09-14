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
    @Query("SELECT t FROM Todo t WHERE t.employeeCode = ?1")
    List<Todo> findByStringEmployeeCode(String employeeCode);

    @Query("SELECT t FROM Todo t WHERE t.title = ?1 AND t.employeeCode = ?2")
    Todo findRowByAssignedColumns(String title,String employeeCode);

    @Query("SELECT t FROM Todo t WHERE t.id = ?1")
    Todo findByStringId(String id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE t.assignedBy = ?1 AND t.assignedTo = ?2 AND t.title = ?3")
    void deleteToDoTask(String assignedBy, String assignedTo, String title);

    @Query("DELETE FROM Todo t WHERE t.assignedBy = ?1 AND t.assignedTo = ?2 AND t.title = ?3")
    Todo checkToDoTaskExist(String assignedBy, String assignedTo, String title);

    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE t.id = ?1")
    void deleteByStringId(String id);
}
