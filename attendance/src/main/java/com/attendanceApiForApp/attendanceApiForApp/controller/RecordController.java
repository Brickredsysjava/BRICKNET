package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import com.attendanceApiForApp.attendanceApiForApp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getRecordById(@PathVariable Long id) {
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
    public ResponseEntity<?> updateRecord(@PathVariable Long id, @RequestBody Records record) {
        Records updatedRecord = recordService.updateRecord(id, record);
        if (updatedRecord != null) {
            return ResponseEntity.ok(updatedRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a record by ID
    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<?> deleteRecord(@PathVariable Long id) {
        boolean deleted = recordService.deleteRecord(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    @GetMapping("/employee/{empId}/month/{month}")
//    public ResponseEntity<List<Records>> getRecordsByEmployeeAndMonth(@PathVariable Long empId, @PathVariable int month) {
//        List<Records> records = recordService.findByEmployeeAndMonth(empId, month);
//        if (!records.isEmpty()) {
//            return ResponseEntity.ok(records);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
