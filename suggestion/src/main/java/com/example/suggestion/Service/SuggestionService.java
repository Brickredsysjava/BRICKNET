package com.example.suggestion.Service;

import com.example.suggestion.DTO.NotificationDto;
import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;

import javax.management.ServiceNotFoundException;
import java.util.List;

public interface SuggestionService {

    public void addSuggestion(SuggestionDto suggestionDto) throws SuggestionException, ServiceNotFoundException;

    List<Suggestion> getAllSuggestions();

    List<Department> getAllDepartments();

    Suggestion updateSuggestionStatus(String id, Status status) throws SuggestionException;

    Suggestion pollSuggestion(String id, Action action);
    
    List<Suggestion> getSuggestionsByStatus(Status status);

    public void deleteSuggestionByID(String id) throws SuggestionException;

    public List<Suggestion> getSuggestionsByDepartment(Department department);

    public List<Suggestion> getSuggestionsByUsername(String Username);

    public List<SuggestionDto> getAllSuggestionsNeedToVerified() throws SuggestionException;

    String adminVerification(String ticket_id,Boolean adminVerified) throws SuggestionException, ServiceNotFoundException;

    public void pushNotification(NotificationDto notificationDto) throws ServiceNotFoundException;

    public String getEmailIdByUserName(String username);



}


