package com.example.suggestion.Service;

import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
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
              .status(suggestionDto.getStatus())
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

    @Override
    public Suggestion updateSuggestionStatus(Long id, Status status) {
        Suggestion suggestion = suggestionRepository.findById(Math.toIntExact(id)).orElse(null);
        if (suggestion != null) {
            suggestion.setStatus(status);
            return suggestionRepository.save(suggestion);
        }
        return null;
    }

    @Override
    public Suggestion pollSuggestion(Long id, String action) {
        Suggestion suggestion = suggestionRepository.findById(Math.toIntExact(id)).orElse(null);
        if (suggestion != null) {
            if ("like".equalsIgnoreCase(String.valueOf(action))) {
                suggestion.setLikeCount(suggestion.getLikeCount() + 1);
            } else if ("dislike".equalsIgnoreCase(String.valueOf(action))) {
                suggestion.setDislikeCount(suggestion.getDislikeCount() + 1);
            }


            return suggestionRepository.save(suggestion);
        }


        return null;
    }

}




