package com.example.suggestion.Controller;

import com.example.suggestion.DTO.GetSuggestionsDTO;
import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Service.SuggestionService;
import com.example.suggestion.Service.SuggestionServiceImplementation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/suggestionPost/api/verification")
public class SuggestionVerificationController
{
    @Autowired
    private SuggestionService suggestionService;


    @GetMapping("/getAllSuggestionsNeedToVerified")
    @Operation(summary = "FOR GETTING THE SUGGESTIONS THAT NEED TO BE VERIFIED")
    public ResponseEntity<List<GetSuggestionsDTO>> getAllSuggestionsNeedToVerified() throws SuggestionException
    {
        List<GetSuggestionsDTO> e1 = suggestionService.getAllSuggestionsNeedToVerified();
        return new ResponseEntity<>( e1, HttpStatus.OK);
    }



    @PostMapping("/adminVerification")
    @Operation(summary = "FOR VERIFICATION FROM THE ADMIN")
    public ResponseEntity<String> adminVerification(@RequestParam String ticket_id, @RequestParam Boolean adminVerified) throws SuggestionException, ServiceNotFoundException {
        String res=suggestionService.adminVerification(ticket_id,adminVerified);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }







}
