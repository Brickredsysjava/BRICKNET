package com.example.suggestion.Service;

import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Suggestion;

import java.util.List;

public interface SuggestionService {

    public void addsuggestion(SuggestionDto suggestionDto);

    List<Suggestion> getAllSuggestions();

    List<Department> getAllDepartments();

}
