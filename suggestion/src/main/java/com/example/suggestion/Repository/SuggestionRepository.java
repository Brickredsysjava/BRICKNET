package com.example.suggestion.Repository;

import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion,Integer> {



    List<Suggestion> findByStatus(Status status);
}
