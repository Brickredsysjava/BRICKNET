package com.example.suggestion.Service;

import com.example.suggestion.DTO.NotificationDto;
import com.example.suggestion.DTO.SuggestionPostDto;
import com.example.suggestion.DTO.GetSuggestionsDTO;
import com.example.suggestion.Exception.SuggestionException;
import com.example.suggestion.Model.Action;
import com.example.suggestion.Model.Department;
import com.example.suggestion.Model.Status;
import com.example.suggestion.Model.Suggestion;
import com.example.suggestion.Repository.SuggestionRepository;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

     @Autowired
    private SuggestionRepository suggestionRepository;

    private WebClient.Builder webClientBuilder;

    public SuggestionServiceImplementation(WebClient.Builder webClientBuilder)
    {
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public void addSuggestion(SuggestionPostDto suggestionDto) throws SuggestionException, ServiceNotFoundException
    {
        if (suggestionDto!=null) {
            Suggestion suggestion = modelMapper.map(suggestionDto,Suggestion.class);
            suggestion.setDateTime(LocalDateTime.now());
            suggestion.setAdminVerified(false);
            suggestion.setStatus(Status.ACTIVE);
            suggestion.setVerificationStatusMessage("False");

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "You have a new Approval Request for a Suggestion \n" + "\n"
                                + "SUGGESTION DETAILS--" + "\n"
                                + "FROM:  " + suggestionDto.getUsername() + "\n"
                                + "DEPARTMENT:  " + suggestionDto.getDepartment() + "\n"
                                + "TITLE:  " + suggestionDto.getTitle() + "\n"
                                + "CLICK HERE for more info" + "\n" +
                                "\n";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient("parthsainis17@gmail.com");
                notificationDto.setTimeStamp(localDateTime);

                pushNotification(notificationDto);
            }catch(Exception e){
                System.out.println("CONNECTION REFUSED");
            }
                finally {
                suggestionRepository.save(suggestion);
            }
        }

        else {
            throw new SuggestionException("PLEASE GIVE PROPER INPUTS");
        }
    }



    @Override
    public List<GetSuggestionsDTO> getAllSuggestions() {

        try{
            List<GetSuggestionsDTO> newList = new ArrayList<>();
            List<GetSuggestionsDTO> dtoList = suggestionRepository.findAll().stream().map(p ->
            {
                GetSuggestionsDTO getSuggestionsDTO = new GetSuggestionsDTO();
                if (p.getAdminVerified()) {
                    getSuggestionsDTO = GetSuggestionsDTO.builder()
                            .ticket_id(p.getTicket_Id())
                            .username(p.getUsername())
                            .title(p.getTitle())
                            .description(p.getDescription())
                            .department(p.getDepartment())
                            .status(p.getStatus())
                            .likeCount((long) p.getLikedEmployee().size())
                            .dislikeCount((long) p.getDisLikedEmployee().size())
                            .likePercentage((double) ((((long) p.getLikedEmployee().size()) / ((long) p.getLikedEmployee().size() + (long) p.getDisLikedEmployee().size())) * 100))
                            .dislikePercentage((double) ((((long) p.getDisLikedEmployee().size()) / ((long) p.getLikedEmployee().size() + (long) p.getDisLikedEmployee().size())) * 100))
                            .adminVerified(p.getAdminVerified())
                            .verificationStatusMessage(p.getVerificationStatusMessage())
                            .build();
                    newList.add(getSuggestionsDTO);
                }
                return getSuggestionsDTO;
            }).toList();

            newList.sort(Comparator.comparing(GetSuggestionsDTO::getSuggestionDateTime).reversed());

            return newList;
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }



    @Override
    public List<Department> getAllDepartments()
    {
        try{
            return Arrays.stream(Department.values())
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }



    @Override
    public Suggestion updateSuggestionStatus(String id, Status status) throws SuggestionException
    {
        Suggestion suggestion = suggestionRepository.findById(id).orElseThrow(()-> new SuggestionException("Data Not Found"));
        try{
            if (suggestion != null) {
                suggestion.setStatus(status);
            }
            return null;
        }
        catch (Exception e) {
            e.getMessage();
        }
        return suggestionRepository.save(suggestion);
    }



    @Override
    public Suggestion pollSuggestion(String id, String employeeCode, Boolean action) throws SuggestionException
    {
        Optional<Suggestion> optionalSuggestion = Optional.ofNullable(suggestionRepository.findById(id).orElseThrow(() -> new SuggestionException("Data Not Found")));
        if (optionalSuggestion.isPresent()) {
            Suggestion suggestion = optionalSuggestion.get();

            if (action == true) {
                suggestion.getLikedEmployee().add(employeeCode);
            } else {
                suggestion.getDisLikedEmployee().add(employeeCode);
            }

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
    public List<GetSuggestionsDTO> getSuggestionsByDepartment(Department department)
    {
        return suggestionRepository.findByDepartment(department);

    }



    @Override
    public List<GetSuggestionsDTO> getAllSuggestionsNeedToVerified() throws SuggestionException
    {
        List<GetSuggestionsDTO> newDtoList = new ArrayList<>();
        List<GetSuggestionsDTO> suggestionDtoList = suggestionRepository.findAll().stream().map(s -> {
            GetSuggestionsDTO getSuggestionsDTO = null;
            if (Objects.equals(s.getVerificationStatusMessage(), "Pending")) {
                getSuggestionsDTO = GetSuggestionsDTO.builder()
                        .ticket_id(s.getTicket_Id())
                        .title(s.getTitle())
                        .description(s.getDescription())
                        .status(s.getStatus())
                        .department(s.getDepartment())
                        .username(s.getUsername())
                        .adminVerified(s.getAdminVerified())
                        .verificationStatusMessage(s.getVerificationStatusMessage())
                        .suggestionDateTime(LocalDateTime.now())
                        .build();
                newDtoList.add(getSuggestionsDTO);
            }
            return getSuggestionsDTO;


        }).toList();

        return newDtoList;
    }



    @Override
    public String adminVerification(String ticket_id, Boolean adminVerified) throws SuggestionException, ServiceNotFoundException {

        Optional<Suggestion> suggestion=suggestionRepository.findById(ticket_id);

        SuggestionPostDto suggestionNew = new SuggestionPostDto();
        suggestionNew.setTitle(suggestion.get().getTitle());
        suggestionNew.setUsername(suggestion.get().getUsername());
        suggestionNew.setDepartment(suggestion.get().getDepartment());


        Suggestion suggestionVerification = suggestionRepository.findById(ticket_id).orElseThrow(() -> new SuggestionException("post not found"));

        if (adminVerified) {
            suggestionVerification.setAdminVerified(true);
            suggestionVerification.setVerificationStatusMessage("true");

try {
    Date date = new Date();
    LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
    String message =
            "\n" + "Hey,  " + suggestionNew.getUsername() + "\n"
                    + "Your Suggestion with TITLE:  " + suggestionNew.getTitle() + "\n"
                    + "About  " + suggestionNew.getDepartment() + "  Department is Accepted.  " + "\n"
                    + "CLICK HERE for more info" + "\n" +
                    "\n";
    NotificationDto notificationDto = new NotificationDto();
    notificationDto.setMessage(message);
    notificationDto.setRecipient(getEmailIdByUserName(suggestionNew.getUsername()));
    notificationDto.setTimeStamp(localDateTime);

    pushNotification(notificationDto);
}catch (Exception e){
    System.out.println("Connection Refused");
}finally {
suggestionRepository.save(suggestionVerification);
}

        } else {
            suggestionVerification.setAdminVerified(false);
            suggestionVerification.setVerificationStatusMessage("false");

            try {
                Date date = new Date();
                LocalDateTime localDateTime = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
                String message =
                        "\n" + "Hey,   " + suggestionNew.getUsername() + "\n"
                                + "Your Suggestion with TITLE:   " + suggestionNew.getTitle() + "\n"
                                + "About  " + suggestionNew.getDepartment() + "  Department" + "  is Rejected.  " + "\n"
                                + "CLICK HERE for more info" + "\n" +
                                "\n";
                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setMessage(message);
                notificationDto.setRecipient(getEmailIdByUserName(suggestionNew.getUsername()));
                notificationDto.setTimeStamp(localDateTime);

                pushNotification(notificationDto);
            }catch (Exception e){
                System.out.println("Connection refused");
            }finally {
suggestionRepository.save(suggestionVerification);
            }

        }
//        suggestionRepository.save(suggestionVerification);

        return "VERIFICATION DONE";
    }



    @Override
    public void pushNotification(NotificationDto notificationDto) throws ServiceNotFoundException
    {
        String jsonBody ="{\"key\": \"value\"}";
        webClientBuilder.baseUrl("http://192.168.0.9:8084/send")
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






