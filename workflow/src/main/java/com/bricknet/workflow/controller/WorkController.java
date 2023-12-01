package com.bricknet.workflow.controller;

import com.bricknet.workflow.Exception.WorkException;
import com.bricknet.workflow.dto.*;
import com.bricknet.workflow.model.Status;
import com.bricknet.workflow.model.User;
import com.bricknet.workflow.model.Work;
import com.bricknet.workflow.service.WorkServiceIMPL;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/work")
@CrossOrigin("*")
public class WorkController {

    @Autowired
    private WorkServiceIMPL workServiceIMPL;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createWorkFlow")
    public ResponseEntity<String> createTask(@Valid   @RequestBody WorkFlowDto workFlowDto) throws WorkException {
        WorkDto workDto=WorkDto.builder()
                .title(workFlowDto.getTitle())
                .description(workFlowDto.getDescription())
                .visibility(workFlowDto.isVisibility())
                .tags(workFlowDto.getTags())
                .estimatedStartDate(workFlowDto.getEstimatedStartDate())
                .estimatedEndDate(workFlowDto.getEstimatedEndDate())
                .build();
        Work work=modelMapper.map(workDto,Work.class);
        work.setEmployeeAssignedBy(workFlowDto.getEmployeeId());
       workServiceIMPL.createWorkflow(work);
        return   ResponseEntity.ok("created");
    }

    @GetMapping("/getAllWorkForUser")
    public ResponseEntity<List<Work>> getAllWorkForUser( @RequestParam String employeeId)  {
        List<Work> t1= workServiceIMPL.getAllworkflowforUser(employeeId);
        return new ResponseEntity<>(t1, HttpStatus.OK);
    }

    @PostMapping("/copyWorkFlow")
    public ResponseEntity<String> copyWorkflow(@Valid @RequestBody CopyWorkFlowDto copyWorkFlowDto) throws WorkException {

      String success =  workServiceIMPL.copyWorkflow(copyWorkFlowDto.getEmployeeId(),copyWorkFlowDto.getWorkId());
        return   ResponseEntity.ok(success);
    }

    @PostMapping("/updateWorkFlow")
    public ResponseEntity<String> updateWorkFlow(@Valid @RequestBody WorkFlowDto workFlowDto) throws WorkException {

        WorkDto workDto=WorkDto.builder()
                .title(workFlowDto.getTitle())
                .description(workFlowDto.getDescription())
                .visibility(workFlowDto.isVisibility())
                .tags(workFlowDto.getTags())
                .estimatedStartDate(workFlowDto.getEstimatedStartDate())
                .estimatedEndDate(workFlowDto.getEstimatedEndDate())
                .build();
        Work work=modelMapper.map(workDto,Work.class);
        workServiceIMPL.updateWork(workFlowDto.getEmployeeId(),work);
        return   ResponseEntity.ok("updated");
    }
    @PostMapping("/softDeleteWorkFlow")
    public ResponseEntity<String> softDeleteWorkFlow(@RequestParam  String workId) {
        return   ResponseEntity.ok( workServiceIMPL.softDeleteWork(workId));
    }
    @DeleteMapping("/deleteWorkFLow")
    public ResponseEntity<String > deleteWorkFlow(@RequestParam  String workId){
        return ResponseEntity.ok(workServiceIMPL.deleteWork(workId));
    }

//    @ResponseBody
//    @GetMapping("/user")
//    private ResponseEntity<?> getUser() {
//        String uri = "http://192.168.1.39:8096/send/getNotification/karl98perfect@gmail.com";
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Send a GET request and get the response as ResponseEntity
//        ResponseEntity<NotificationDto[]> responseEntity = restTemplate.getForEntity(uri, NotificationDto[].class);
//        NotificationDto[] notificationArray = responseEntity.getBody();
//   return new ResponseEntity<>( notificationArray,HttpStatus.OK);
////        // Convert the array to a List for better manipulation
////        List<NotificationDto> notificationList = Arrays.asList(notificationArray);
////
////        // Print the response
////        System.out.println(notificationList);
////
////        // Alternatively, you can loop through the list and process each notification
////        for (NotificationDto notification : notificationList) {
////            System.out.println("Message: " + notification.getMessage());
////            System.out.println("Timestamp: " + notification.getTimeStamp());
////            System.out.println();
////        }
//
//    }
//    @PostMapping("/sendmail")
//    public void sendMail() {
//        String url = "http://192.168.1.39:8096/send/email";
//        String requestBody = "{\r\n\r\n    \"message\": \"This is a test1234 email notification.\",\r\n\r\n    \"recipient\": \"tubbu32@gmail.com\"\r\n\r\n}";
//
//        // Prepare the request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Create an HttpEntity with headers and body
//        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
//
//        // Create a RestTemplate instance
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Send the POST request and get the response
//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                requestEntity,
//                String.class
//        );
//        // Print the response
//        System.out.println("Response status code: " + responseEntity.getStatusCodeValue());
//        System.out.println("Response body: " + responseEntity.getBody());
//
//
//    }

}
