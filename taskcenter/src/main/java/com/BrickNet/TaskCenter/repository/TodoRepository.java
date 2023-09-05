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
    List<Todo> findByStringId(String employeeCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE t.employeeCode = ?1 AND t.taskName = ?2")
    void deleteByStringIdForCreateToDo(String employeeCode, String taskName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Todo t WHERE  t.taskName = ?1 AND t.employeeCode = ?2")
    void deleteByStringIdForAssignedToDoUser(String taskName, String employeeCode);

    @Query("SELECT t FROM Todo t WHERE t.taskName = ?1 AND t.employeeCode = ?2")
    Todo findRowByAssignedToColumn(String taskName,String employeeCode);

    @Query("SELECT t FROM Todo t WHERE t.id = ?1")
    Todo findTodoById(Integer id);

    @Query("SELECT t FROM Todo t WHERE t.taskName = ?1")
    Todo findByStringTaskName(String taskName);

    @Query("SELECT t FROM Todo t WHERE t.assignTo = ?1")
    Todo findByStringAssignTo(String assignTo);

}
