package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckIn;
import com.attendanceApiForApp.attendanceApiForApp.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/checkins")
public class CheckInController {

    private final CheckInService checkInService;

    @Autowired
    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    // Endpoint to get all check-ins
    @GetMapping("/getAllCheckIns")
    public ResponseEntity<?> getAllCheckIns() {
        return ResponseEntity.ok(checkInService.getAllCheckIns());
    }

    // Endpoint to get a single check-in by ID
    @GetMapping("/getCheckInById/{id}")
    public ResponseEntity<?> getCheckInById(@PathVariable Long id) {
        CheckIn checkIn = checkInService.getCheckInById(id);
        if (checkIn != null) {
            return ResponseEntity.ok(checkIn);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to create a new check-in
    @PostMapping("/createCheckIn")
    public ResponseEntity<?> createCheckIn(@RequestBody CheckIn checkIn) {
        CheckIn createdCheckIn = checkInService.createCheckIn(checkIn);
        return ResponseEntity.ok(createdCheckIn);
    }

    // Endpoint to update an existing check-in
    @PutMapping("/updateCheckIn/{id}")
    public ResponseEntity<?> updateCheckIn(@PathVariable Long id, @RequestBody CheckIn checkIn) {
        CheckIn updatedCheckIn = checkInService.updateCheckIn(id, checkIn);
        if (updatedCheckIn != null) {
            return ResponseEntity.ok(updatedCheckIn);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a check-in by ID
    @DeleteMapping("/deleteCheckIn/{id}")
    public ResponseEntity<?> deleteCheckIn(@PathVariable Long id) {
        boolean deleted = checkInService.deleteCheckIn(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getFirstCheckInTime/{emp_id}/{dates}")
    public ResponseEntity<List<String>> getFirstCheckInTimes(
            @PathVariable Long emp_id,
            @RequestParam("dates") List<String> dates ) {

        List<String> firstCheckTimes = checkInService.findFirstCheckInTimes(emp_id, dates);
        System.out.println(dates);
        if (!firstCheckTimes.isEmpty()) {
            return new ResponseEntity<>(firstCheckTimes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

