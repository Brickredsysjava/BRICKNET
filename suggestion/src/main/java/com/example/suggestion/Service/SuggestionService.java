package com.example.suggestion.Service;

import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;

import java.util.List;

public interface SuggestionService {

    public void addsuggestion(SuggestionDto suggestionDto);

    List<Suggestion> getAllSuggestions();

    List<Department> getAllDepartments();

    Suggestion updateSuggestionStatus(Long id, Status status);

    Suggestion pollSuggestion(Long id, Action action);

    List<Suggestion> getSuggestionsByStatus(Status status);

    public void deleteSuggestionbyID(Long id) throws SuggestionException;


}


