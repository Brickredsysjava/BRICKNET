package com.example.suggestion.Repository;

import com.example.suggestion.Model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion,Integer> {
}
