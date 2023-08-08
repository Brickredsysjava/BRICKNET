package com.example.suggestion.Service;

import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import com.example.suggestion.Repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
              .suggestionDate(LocalDateTime.now().toLocalDate())
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
    public Suggestion pollSuggestion(Long id, Action action)  {
        Optional<Suggestion> optionalSuggestion = suggestionRepository.findById(Math.toIntExact(id));
        if (optionalSuggestion.isPresent()) {
            Suggestion suggestion = optionalSuggestion.get();

            if (action == Action.LIKE) {
                suggestion.setLikeCount(suggestion.getLikeCount() + 1);
            } else if (action == Action.DISLIKE) {
                suggestion.setDislikeCount(suggestion.getDislikeCount() + 1);
            }

            int totalVotes = suggestion.getLikeCount() + suggestion.getDislikeCount();
            suggestion.setLikePercentage(totalVotes > 0 ? (double) suggestion.getLikeCount() / totalVotes * 100 : 0);
            suggestion.setDislikePercentage(totalVotes > 0 ? (double) suggestion.getDislikeCount() / totalVotes * 100 : 0);

            return suggestionRepository.save(suggestion) ;
        }
        return null;

    }


    @Override
    public List<Suggestion> getSuggestionsByStatus(Status status) {

        return suggestionRepository.findByStatus(status);
    }

}




