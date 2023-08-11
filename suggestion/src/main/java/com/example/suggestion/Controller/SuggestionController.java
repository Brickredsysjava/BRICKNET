package com.example.suggestion.Controller;


import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import com.example.suggestion.Service.SuggestionServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
@CrossOrigin("*")
public class SuggestionController {

    @Autowired
    private SuggestionServiceImplementation suggestionServiceImplementation;

    @PostMapping("/addsuggestion")
    public ResponseEntity<String> addsuggestion(@RequestBody SuggestionDto suggestionDto) throws SuggestionException {

        suggestionServiceImplementation.addsuggestion(suggestionDto);
        return ResponseEntity.ok("SUGGESTION SEND SUCCESSFULLY");
    }


    @GetMapping("/getallmember")
     public ResponseEntity<List<Suggestion>> getallsuggestions() throws SuggestionException{
        List<Suggestion> s1=suggestionServiceImplementation.getAllSuggestions();
        return new ResponseEntity<List<Suggestion>>(s1, HttpStatus.OK);

    }


    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = suggestionServiceImplementation.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @PutMapping("/{ticket_id}/status")
    public Suggestion updateSuggestionStatus(@PathVariable Long  ticket_id, @RequestParam Status status) {
        return suggestionServiceImplementation.updateSuggestionStatus( ticket_id, status);
    }

    @PostMapping("/{ticket_id}/poll")
    public Suggestion pollSuggestion(@PathVariable Long ticket_id, @RequestParam Action action) {
        return suggestionServiceImplementation.pollSuggestion(ticket_id, Action.valueOf(String.valueOf(action)));
    }


    @GetMapping("/suggestionByStatus")
    public List<Suggestion> getSuggestionsByStatus(@RequestParam Status status) {
        return suggestionServiceImplementation.getSuggestionsByStatus(status);
    }

    @DeleteMapping("/Delete/{ticket_Id}")
    public ResponseEntity<String> deleteEmployeeID(@PathVariable Long ticket_Id) throws SuggestionException {
        suggestionServiceImplementation.deleteSuggestionbyID(ticket_Id);
        return  ResponseEntity.ok("SUGGESTION DELETED SUCCESSFULLY");
    }


}
