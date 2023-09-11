package com.example.suggestion.Repository;

import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion,String>
{



    @Query("SELECT s FROM Suggestion s WHERE s.status = :enumParam  AND s.adminVerified=true")
    List<Suggestion> findByStatus(@Param("enumParam")Status status);

    @Query("SELECT s FROM Suggestion s WHERE s.username=:username")
    List<Suggestion> findByUsername(String username);

    @Query("SELECT DISTINCT s.department FROM Suggestion s WHERE s.department <> 'All_Suggestions'")
    List<String> findAllDepartments();

    @Query("SELECT s FROM   Suggestion s WHERE s.department=:department AND s.adminVerified=true")
    List<Suggestion> findByDepartment(Department department);


}
