package com.example.suggestion.Controller;



import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import com.example.suggestion.Service.SuggestionService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/suggestionPost/api/suggestions")
public class SuggestionController
{

    @Autowired
    private SuggestionService suggestionService;


    //API TO ADD A SUGGESTION
    @PostMapping("/addSuggestion")
    public ResponseEntity<String> addSuggestion(@RequestBody SuggestionDto suggestionDto) throws SuggestionException, ServiceNotFoundException {

        suggestionService.addSuggestion(suggestionDto);
        return ResponseEntity.ok("SUGGESTION SEND SUCCESSFULLY");
    }



    //API TO SEE ALL THE ACTIVE SUGGESTIONS
    @GetMapping("/getAllSuggestions")
     public ResponseEntity<List<Suggestion>> getAllSuggestions() throws SuggestionException
    {
        List<Suggestion> s1=suggestionService.getAllSuggestions();
        return new ResponseEntity<>(s1, HttpStatus.OK);

    }



    //API TO GET ALL THE DEPARTMENTS AVAILABLE
    @GetMapping("/allDepartments")
    public ResponseEntity<List<Department>> getAllDepartmentsExceptAllSuggestions()
    {
        List<Department> departments = suggestionService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }



    //API FOR ADMIN TO UPDATE THE STATUS OF ALL SUGGESTION
    @PutMapping("/{ticket_id}/status")
    public Suggestion updateSuggestionStatus(@PathVariable String  ticket_id, @RequestParam Status status) throws SuggestionException
    {
        return suggestionService.updateSuggestionStatus( ticket_id, status);
    }



    //API FOR DOING POLLING FOR A SUGGESTION
    @PostMapping("/{ticket_id}/poll")
    public Suggestion pollSuggestion(@PathVariable String ticket_id, @RequestParam Action action)
    {
        return suggestionService.pollSuggestion(ticket_id, Action.valueOf(String.valueOf(action)));
    }



    //API TO GET ALL THE SUGGESTION BASED ON THE STATUS
    @GetMapping("/suggestionByStatus")
    public List<Suggestion> getSuggestionsByStatus(@RequestParam Status status)
    {
        return suggestionService.getSuggestionsByStatus(status);
    }



    //API TO DELETE ANY SUGGESTION
    @DeleteMapping("/Delete/{ticket_Id}")
    public ResponseEntity<String> deleteEmployeeID(@PathVariable String ticket_Id) throws SuggestionException
    {
        suggestionService.deleteSuggestionByID(ticket_Id);
        return  ResponseEntity.ok("SUGGESTION DELETED SUCCESSFULLY");
    }



    //API TO GET SUGGESTION BASED ON THEIR DEPARTMENT
    @GetMapping("/findSuggestionByDepartment")
    public ResponseEntity<List<Suggestion>> findSuggestionByDepartment(@RequestParam Department department)
    {

        List<Suggestion> suggestions;

        if (department == Department.All_Suggestions) {
            suggestions = suggestionService.getAllSuggestions();
        } else {
            // Return suggestions filtered by department
            suggestions = suggestionService.getSuggestionsByDepartment(department);
        }

        return ResponseEntity.ok(suggestions);
    }



    //API TO GET SUGGESTIONS OF A PARTICULAR USER
    @GetMapping("/suggestionByUsername")
    public List<Suggestion> getSuggestionsByUsername(@RequestParam String username)
    {
        return suggestionService.getSuggestionsByUsername(username);
    }

    //Test API
    @GetMapping("/test")
    public String getTest(){
        return "The suggestion is up and runining";
    }



}


