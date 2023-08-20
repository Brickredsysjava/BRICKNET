package com.bricknet.workflow.controller;

import com.bricknet.workflow.Exception.WorkException;
import com.bricknet.workflow.dto.EmailDto;
import com.bricknet.workflow.dto.NotificationDto;
import com.bricknet.workflow.dto.WorkDto;
import com.bricknet.workflow.model.Status;
import com.bricknet.workflow.model.User;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.service.WorkServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/work")
@CrossOrigin("*")
public class WorkController {

    @Autowired
    private WorkServiceIMPL workServiceIMPL;

    @PostMapping("/createWorkFlow")
    public ResponseEntity<String> createTask(@RequestParam Long userId,@RequestBody WorkDto workDto) throws WorkException {
        Work work=Work.builder()
                        .title(workDto.getTitle())
                          .visibility(workDto.isVisibility())
                        .tags(workDto.getTags())
     .description(workDto.getDescription())
                     .userAssignedTeam(workDto.getUserAssignedTeam())
            .status(workDto.getStatus())
                   .creatorId(userId)
                 .estimatedStartDate(workDto.getEstimatedStartDate())
                .estimatedEndDate(workDto.getEstimatedEndDate())
                .tasks(workDto.getTasks())
                                                                                                .build();
       workServiceIMPL.createWorkflow(work);
        return   ResponseEntity.ok("created");
    }

    @GetMapping("/getAllWorkForUser")
    public ResponseEntity<List<Work>> getAllWorkForUser(@RequestParam Long userId)  {

        List<Work> t1= workServiceIMPL.getAllworkflowforUser(userId);
        return new ResponseEntity<>(t1, HttpStatus.OK);

    }

    @PostMapping("/copyWorkFlow")
    public ResponseEntity<String> copyWorkflow(@RequestParam Long userId,@RequestBody WorkDto workDto) {
        Work work=Work.builder()
                .title(workDto.getTitle())
                .visibility(true)
                .active(true)
                .tags(workDto.getTags())
                .description(workDto.getDescription())
                .userAssignedTeam(workDto.getUserAssignedTeam())
                .status(Status.Initial)
                .creatorId(userId)
                .estimatedStartDate(workDto.getEstimatedStartDate())
                .estimatedEndDate(workDto.getEstimatedEndDate())
                .tasks(workDto.getTasks())
                .build();
      String success =  workServiceIMPL.copyWorkflow(userId,work);
        return   ResponseEntity.ok(success);
    }

    @PostMapping("/updateWorkFlow")
    public ResponseEntity<String> updateWorkFlow(@RequestBody Work work) {
        workServiceIMPL.updateWork(work);
        return   ResponseEntity.ok("updated");
    }
    @PostMapping("/softDeleteWorkFlow")
    public ResponseEntity<String> softDeleteWorkFlow(@RequestBody Long workId) {
        return   ResponseEntity.ok( workServiceIMPL.softDeleteWork(workId));
    }
    @DeleteMapping("/deleteWorkFLow")
    public ResponseEntity<String > deleteWorkFlow(@RequestParam Long workId){
        return ResponseEntity.ok(workServiceIMPL.deleteWork(workId));
    }
    @RequestMapping("/user")
    @ResponseBody
    @GetMapping
    private ResponseEntity<?> getUser() {
        String uri = "http://192.168.1.39:8096/send/getNotification/karl98perfect@gmail.com";
        RestTemplate restTemplate = new RestTemplate();

        // Send a GET request and get the response as ResponseEntity
        ResponseEntity<NotificationDto[]> responseEntity = restTemplate.getForEntity(uri, NotificationDto[].class);
        NotificationDto[] notificationArray = responseEntity.getBody();
   return new ResponseEntity<>( notificationArray,HttpStatus.OK);
//        // Convert the array to a List for better manipulation
//        List<NotificationDto> notificationList = Arrays.asList(notificationArray);
//
//        // Print the response
//        System.out.println(notificationList);
//
//        // Alternatively, you can loop through the list and process each notification
//        for (NotificationDto notification : notificationList) {
//            System.out.println("Message: " + notification.getMessage());
//            System.out.println("Timestamp: " + notification.getTimeStamp());
//            System.out.println();
//        }

    }
    @PostMapping("/sendmail")
    public void sendMail() {
        String url = "http://192.168.1.39:8096/send/email";
        String requestBody = "{\r\n\r\n    \"message\": \"This is a test1234 email notification.\",\r\n\r\n    \"recipient\": \"tubbu32@gmail.com\"\r\n\r\n}";

        // Prepare the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create an HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send the POST request and get the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        // Print the response
        System.out.println("Response status code: " + responseEntity.getStatusCodeValue());
        System.out.println("Response body: " + responseEntity.getBody());

        
    }

}
