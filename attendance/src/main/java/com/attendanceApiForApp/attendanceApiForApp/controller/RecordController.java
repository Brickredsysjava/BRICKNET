package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import com.attendanceApiForApp.attendanceApiForApp.service.RecordService;
import jakarta.persistence.NoResultException;
import jakarta.validation.GroupSequence;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/records")
public class RecordController {
    @Autowired
    private final RecordService recordService;


    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // Endpoint to get all records
    @GetMapping("/getAllRecords")
    public ResponseEntity<?> getAllRecords() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }

    // Endpoint to get a single record by ID
    @GetMapping("/getRecordById/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable String id) {
        Records record = recordService.getRecordById(id);
        if (record != null) {
            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to create a new record
    @PostMapping("/createRecord")
    public ResponseEntity<?> createRecord(@RequestBody Records record) {
        Records createdRecord = recordService.createRecord(record);
        return ResponseEntity.ok(createdRecord);
    }

    // Endpoint to update an existing record
    @PutMapping("/updateRecord/{id}")
    public ResponseEntity<?> updateRecord(@PathVariable String id, @RequestBody Records record) {
        Records updatedRecord = recordService.updateRecord(id, record);
        if (updatedRecord != null) {
            return ResponseEntity.ok(updatedRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a record by ID
    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable String id) {
        boolean deleted = recordService.deleteRecord(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/getRecordIdWithAutoId/{auto_id}/{todayDate}")
    public ResponseEntity<String> getRecordIdWithAutoId(@PathVariable String auto_id, @PathVariable("todayDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date todayDate) {
        try {
            String record_id = recordService.getRecordId(auto_id, todayDate);
            return new ResponseEntity<>(record_id, HttpStatus.OK);
        } catch (NoResultException e) {
            // Handle the case when no result is found
            return new ResponseEntity<>("No record found for the given parameters", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions that might occur
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateDurationInRecord/{recordId}/{durations}")
    public ResponseEntity<String> updateDurationInRecord(
            @PathVariable String recordId,
            @PathVariable String durations
    ) {
        recordService.updateDurationInRecord(durations, recordId);
        return ResponseEntity.ok("Duration in record updated successfully.");
    }

    @PutMapping("/updateStatusInRecord/{recordId}/{status}")
    public ResponseEntity<String> updateStatusInRecord(
            @PathVariable String recordId,
            @PathVariable boolean status
    )
    {
        recordService.updateStatusInRecord(status,recordId);
        return ResponseEntity.ok("Status in record updated successfully.");
    }
    @PutMapping("/updateTimeZoneInRecord/{recordId}/{timezone}")
    public ResponseEntity<String> updateTimeZoneInRecord(
            @RequestParam("recordId") String recordId,
            @RequestParam("timezone") String timezone
    )
    {
        recordService.updateTimeZoneInRecord(recordId, timezone);
        return new ResponseEntity<>("It is updated successfully", HttpStatus.OK);
    }

    @GetMapping("/status/{record_id}")
    public ResponseEntity<Boolean> getStatus(@PathVariable String record_id) {
        try {
            boolean status = recordService.getStatusValue(record_id);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @GetMapping("/isSelectedTimeZone/{record_id}")
    public  ResponseEntity<Boolean> getIsSelectedTimeZone(@PathVariable String record_id){
        try{
            boolean status = recordService.getIsSelecteTimeZone(record_id);
            return new ResponseEntity<>(status,HttpStatus.OK);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @GetMapping("/getTimeZone/{record_id}")
    public  ResponseEntity<String> getTimeZone(@PathVariable String record_id){
        try {
            String res = recordService.getTimeZone(record_id);
            return  new ResponseEntity<>(res,HttpStatus.OK);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occored");
        }
    }

    @PutMapping("/updateIsSelectTimeZoneInRecord/{recordId}/{time_zone_selected}")
    public ResponseEntity<String> updateIsSelectTimeZoneInRecord(
            @PathVariable String recordId,
            @PathVariable boolean time_zone_selected
    )
    {
        recordService.updateIsSelectTimeZoneInRecord(time_zone_selected,recordId);
        return ResponseEntity.ok("Status in record updated successfully.");
    }


}
