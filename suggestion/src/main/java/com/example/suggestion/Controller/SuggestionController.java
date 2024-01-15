package com.example.suggestion.Controller;



import com.example.suggestion.DTO.SuggestionPostDto;
import com.example.suggestion.DTO.GetSuggestionsDTO;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import com.example.suggestion.Service.SuggestionService;


import jakarta.validation.Valid;
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
    public ResponseEntity<String> addSuggestion(@Valid @RequestBody SuggestionPostDto suggestionDto) throws SuggestionException, ServiceNotFoundException {

        suggestionService.addSuggestion(suggestionDto);
        return ResponseEntity.ok("SUGGESTION SEND SUCCESSFULLY");
    }


    //API TO SEE ALL THE ACTIVE SUGGESTIONS
    @GetMapping("/getAllSuggestions")
     public ResponseEntity<List<GetSuggestionsDTO>> getAllSuggestions() throws SuggestionException
    {
        List<GetSuggestionsDTO> s1=suggestionService.getAllSuggestions();
        if(s1!=null) {
            return new ResponseEntity<>(s1, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(s1, HttpStatus.UNAUTHORIZED);
        }

    }



    //API TO GET ALL THE DEPARTMENTS AVAILABLE
    @GetMapping("/allDepartments")
    public ResponseEntity<List<Department>> getAllDepartmentsExceptAllSuggestions()
    {
        List<Department> departments = suggestionService.getAllDepartments();
        if(departments!=null) {
            return ResponseEntity.ok(departments);
        }
        else {
            return new ResponseEntity<>(departments, HttpStatus.UNAUTHORIZED);

        }
    }



    //API FOR ADMIN TO UPDATE THE STATUS OF ALL SUGGESTION
    @PostMapping("/status")
    public Suggestion updateSuggestionStatus(@RequestParam("ticketId") String ticket_id, @RequestParam Status status) throws SuggestionException
    {
        return suggestionService.updateSuggestionStatus( ticket_id, status);
    }

    //API FOR DOING POLLING FOR A SUGGESTION
    @PostMapping("/poll")
    public Suggestion pollSuggestion(@RequestParam("ticketId") String ticket_id,@RequestParam("employeeCode") String employeeCode ,@RequestParam("action") Boolean action ) throws SuggestionException
    {
        return suggestionService.pollSuggestion(ticket_id,employeeCode,action);
    }

    //API TO GET ALL THE SUGGESTION BASED ON THE STATUS
    @GetMapping("/suggestionByStatus")
    public List<Suggestion> getSuggestionsByStatus(@RequestParam Status status)
    {
        return suggestionService.getSuggestionsByStatus(status);
    }



    //API TO DELETE ANY SUGGESTION
    @DeleteMapping("/Delete")
    public ResponseEntity<String> deleteEmployeeID(@RequestParam("ticketId") String ticket_Id) throws SuggestionException
    {
        suggestionService.deleteSuggestionByID(ticket_Id);
        return  ResponseEntity.ok("SUGGESTION DELETED SUCCESSFULLY");
    }



    //API TO GET SUGGESTION BASED ON THEIR DEPARTMENT
    @GetMapping("/findSuggestionByDepartment")
    public ResponseEntity<List<GetSuggestionsDTO>> findSuggestionByDepartment(@RequestParam Department department)
    {

        List<GetSuggestionsDTO> suggestions;

            // Return suggestions filtered by department
            suggestions = suggestionService.getSuggestionsByDepartment(department);

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


