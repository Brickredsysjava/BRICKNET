package com.example.suggestion.Service;

import com.example.suggestion.DTO.NotificationDto;
import com.example.suggestion.DTO.SuggestionDto;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import com.example.suggestion.Repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.ServiceNotFoundException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SuggestionServiceImplementation implements SuggestionService{

     @Autowired
    SuggestionRepository suggestionRepository;

    private WebClient.Builder webClientBuilder;

    public SuggestionServiceImplementation(WebClient.Builder webClientBuilder)
    {
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public void addSuggestion(SuggestionDto suggestionDto) throws SuggestionException, ServiceNotFoundException
    {

        if (suggestionDto!=null) {
            Suggestion suggestion = Suggestion.builder()
                    .username(suggestionDto.getUsername())
                    .subjectTitle(suggestionDto.getSubjectTitle())
                    .description(suggestionDto.getDescription())
                    .department(suggestionDto.getDepartment())
                    .status(suggestionDto.getStatus())
                    .suggestionDate(LocalDateTime.now().toLocalDate())
                    .adminVerified(false)
                    .verificationStatusMessage("Pending")
                    .build();


            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "You have a new Approval Request for a Suggestion \n" + "\n"
                                + "SUGGESTION DETAILS--" + "\n"
                                + "FROM:  " + suggestionDto.getUsername() + "\n"
                                + "DEPARTMENT:  " + suggestionDto.getDepartment() + "\n"
                                + "TITLE:  " + suggestionDto.getSubjectTitle() + "\n"
                                + "CLICK HERE for more info" + "\n" +
                                "\n";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient("parthsainis17@gmail.com");
                notificationDto.setTimeStamp(localDateTime);

                pushNotification(notificationDto);
            }finally {
                suggestionRepository.save(suggestion);
                System.out.println("CONNECTION REFUSED");

            }
        }

        else {
            throw new SuggestionException("PLEASE GIVE PROPER INPUTS");
        }
    }



    @Override
    public List<Suggestion> getAllSuggestions()
    {
    List<Suggestion> newList = new ArrayList<>();
    List<Suggestion> dtoList = suggestionRepository.findAll().stream().map(p->
    {
        Suggestion suggestion = null;
        if(p.getAdminVerified()){
            suggestion = Suggestion.builder()
                    .ticket_id(p.getTicket_id())
                    .username(p.getUsername())
                    .subjectTitle(p.getSubjectTitle())
                    .description(p.getDescription())
                    .department(p.getDepartment())
                    .suggestionDate(p.getSuggestionDate())
                    .status(p.getStatus())
                    .likeCount(p.getLikeCount())
                    .dislikeCount(p.getDislikeCount())
                    .likePercentage(p.getLikePercentage())
                    .dislikePercentage(p.getDislikePercentage())
                    .adminVerified(p.getAdminVerified())
                    .verificationStatusMessage(p.getVerificationStatusMessage())
                    .build();
            newList.add(suggestion);
        }
        return suggestion;
    }).toList();
    return newList;
    }



    @Override
    public List<Department> getAllDepartments()
    {
        return Arrays.stream(Department.values())
                .filter(department -> department != Department.All_Suggestions)
                .collect(Collectors.toList());

    }



    @Override
    public Suggestion updateSuggestionStatus(String id, Status status) throws SuggestionException
    {
        Suggestion suggestion = suggestionRepository.findById(id).orElse(null);
        if (suggestion != null) {
            suggestion.setStatus(status);
            return suggestionRepository.save(suggestion);
        }
        return null;
    }



    @Override
    public Suggestion pollSuggestion(String id, Action action)
    {
        Optional<Suggestion> optionalSuggestion = suggestionRepository.findById(id);
        if (optionalSuggestion.isPresent()) {
            Suggestion suggestion = optionalSuggestion.get();

            if (action == Action.LIKE) {
                suggestion.setLikeCount(suggestion.getLikeCount() + 1);
            } else if (action == Action.DISLIKE) {
                suggestion.setDislikeCount(suggestion.getDislikeCount() + 1);
            }

            int totalVotes = suggestion.getLikeCount() + suggestion.getDislikeCount();
            suggestion.setLikePercentage(totalVotes > 0 ? (int) ((double) suggestion.getLikeCount() / totalVotes * 100) : 0);
            suggestion.setDislikePercentage(totalVotes > 0 ? (int) ((double) suggestion.getDislikeCount() / totalVotes * 100) : 0);

            return suggestionRepository.save(suggestion) ;
        }
        return null;

    }



    @Override
    public List<Suggestion> getSuggestionsByStatus(Status status)
    {

        return suggestionRepository.findByStatus(status);
    }



    @Override
    public void deleteSuggestionByID(String id) throws SuggestionException
    {

        suggestionRepository.deleteById(String.valueOf(id));
    }



    @Override
    public List<Suggestion> getSuggestionsByDepartment(Department department)
    {
        return suggestionRepository.findByDepartment(department);

    }



    @Override
    public List<SuggestionDto> getAllSuggestionsNeedToVerified() throws SuggestionException
    {
        List<SuggestionDto> newDtoList = new ArrayList<>();
        List<SuggestionDto> suggestionDtoList = suggestionRepository.findAll().stream().map(s -> {
            SuggestionDto suggestionGetDto = null;
            if (Objects.equals(s.getVerificationStatusMessage(), "Pending")) {
                suggestionGetDto = SuggestionDto.builder()
                        .ticket_id(s.getTicket_id())
                        .subjectTitle(s.getSubjectTitle())
                        .description(s.getDescription())
                        .status(s.getStatus())
                        .department(s.getDepartment())
                        .username(s.getUsername())
                        .adminVerified(s.getAdminVerified())
                        .verificationStatusMessage(s.getVerificationStatusMessage())
                        .suggestionDate(LocalDateTime.now().toLocalDate())
                        .build();
                newDtoList.add(suggestionGetDto);
            }
            return suggestionGetDto;


        }).toList();

        return newDtoList;
    }



    @Override
    public String adminVerification(String ticket_id, Boolean adminVerified) throws SuggestionException, ServiceNotFoundException {

        Optional<Suggestion> suggestion=suggestionRepository.findById(ticket_id);

        SuggestionDto suggestionNew = new SuggestionDto();
        suggestionNew.setSubjectTitle(suggestion.get().getSubjectTitle());
        suggestionNew.setUsername(suggestion.get().getUsername());
        suggestionNew.setDepartment(suggestion.get().getDepartment());


        Suggestion suggestionVerification = suggestionRepository.findById(ticket_id).orElseThrow(() -> new SuggestionException("post not found"));

        if (adminVerified) {
            suggestionVerification.setAdminVerified(true);
            suggestionVerification.setVerificationStatusMessage("Approved");


            Date date = new Date();
            LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
            String message =
                    "\n"+    "Hey,  " + suggestionNew.getUsername() + "\n"
                            + "Your Suggestion with TITLE:  " + suggestionNew.getSubjectTitle() + "\n"
                            +"About  "+suggestionNew.getDepartment()+"  Department is Accepted.  "+ "\n"
                            + "CLICK HERE for more info" + "\n" +
                            "\n";
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setMessage(message);
            notificationDto.setRecipient(getEmailIdByUserName(suggestionNew.getUsername()));
            notificationDto.setTimeStamp(localDateTime);

            pushNotification(notificationDto);


        }

        else {
            suggestionVerification.setAdminVerified(false);
            suggestionVerification.setVerificationStatusMessage("Rejected");

            Date date = new Date();
            LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
            String message =
                    "\n"+  "Hey,   " + suggestionNew.getUsername() + "\n"
                            + "Your Suggestion with TITLE:   " + suggestionNew.getSubjectTitle() + "\n"
                            +"About  "+suggestionNew.getDepartment()+"  Department" +"  is Rejected.  "+ "\n"
                            + "CLICK HERE for more info" + "\n" +
                            "\n";
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setMessage(message);
            notificationDto.setRecipient(getEmailIdByUserName(suggestionNew.getUsername()));
            notificationDto.setTimeStamp(localDateTime);

            pushNotification(notificationDto);

        }
        suggestionRepository.save(suggestionVerification);

        return "VERIFICATION DONE";
    }



    @Override
    public void pushNotification(NotificationDto notificationDto) throws ServiceNotFoundException
    {
        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.1.71:8080/send")
                .build().post().uri("/email").bodyValue(notificationDto).retrieve().toBodilessEntity().block();
    }

    @Override
    public String getEmailIdByUserName(String username) {
        HashMap<String, String> emailIdHashMap = new HashMap<>();
        emailIdHashMap.put("Parth", "parthsainis17@gmail.com");
        emailIdHashMap.put("Piyush", "piyushrai558@gmail.com");
        emailIdHashMap.put("Pankaj", "karl98perfect@gmail.com");
        emailIdHashMap.put("Debayan", "tubbu32@gmail.com");
        String email = emailIdHashMap.get(username);
        return email;
    }


    @Override
    public List<Suggestion> getSuggestionsByUsername(String Username)
    {

        return suggestionRepository.findByUsername(Username);
    }




}






