package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckOut;
import com.attendanceApiForApp.attendanceApiForApp.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/checkouts")
public   class CheckOutController {

    private final CheckOutService checkOutService;

    @Autowired
    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    // Endpoint to get all check-outs
    @GetMapping("/getAllCheckOuts")
    public ResponseEntity<?> getAllCheckOuts() {
        return ResponseEntity.ok(checkOutService.getAllCheckOuts());
    }

    // Endpoint to get a single check-out by ID
    @GetMapping("/getCheckOutById/{id}")
    public ResponseEntity<?> getCheckOutById(@PathVariable Long id) {
        CheckOut checkOut = checkOutService.getCheckOutById(id);
        if (checkOut != null) {
            return ResponseEntity.ok(checkOut);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to create a new check-out
    @PostMapping("/createCheckOut")
    public ResponseEntity<?> createCheckOut(@RequestBody CheckOut checkOut) {
        CheckOut createdCheckOut = checkOutService.createCheckOut(checkOut);
        return ResponseEntity.ok(createdCheckOut);
    }

    // Endpoint to update an existing check-out
    @PutMapping("/updateCheckOut/{id}")
    public ResponseEntity<?> updateCheckOut(@PathVariable Long id, @RequestBody CheckOut checkOut) {
        CheckOut updatedCheckOut = checkOutService.updateCheckOut(id, checkOut);
        if (updatedCheckOut != null) {
            return ResponseEntity.ok(updatedCheckOut);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a check-out by ID
    @DeleteMapping("/deleteCheckOut/{id}")
    public ResponseEntity<?> deleteCheckOut(@PathVariable Long id) {
        boolean deleted = checkOutService.deleteCheckOut(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getLastCheckOutTime")
    public  ResponseEntity<String> getLastCheckOutTime(){
        return  new ResponseEntity<>(this.checkOutService.findLasttCheckOutTime(),HttpStatus.OK);
    }

}

