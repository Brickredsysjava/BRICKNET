package com.example.suggestion.Service;

import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Suggestion;
import com.example.suggestion.Repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class SuggestionServiceImplementation implements SuggestionService{

     @Autowired
    SuggestionRepository suggestionRepository;


    @Override
    public void addsuggestion(SuggestionDto suggestionDto) {
      Suggestion suggestion=Suggestion.builder()
              .subjectTitle(suggestionDto.getSubjectTitle())
              .description(suggestionDto.getDescription())
              .department(suggestionDto.getDepartment())
              .build();

             suggestionRepository.save(suggestion);
    }

    @Override
    public List<Suggestion> getAllSuggestions() {

        return suggestionRepository.findAll();

    }

    @Override
    public List<Department> getAllDepartments() {
        return Arrays.asList(Department.values());
    }


}

