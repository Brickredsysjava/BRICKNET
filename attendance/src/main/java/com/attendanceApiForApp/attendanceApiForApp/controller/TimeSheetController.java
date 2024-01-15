package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.Exception.TimesheetException;
import com.attendanceApiForApp.attendanceApiForApp.dto.TimeSheetDto;
import com.attendanceApiForApp.attendanceApiForApp.model.TimeSheet;
import com.attendanceApiForApp.attendanceApiForApp.service.TimeSheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;


@CrossOrigin("*")
@RestController
@RequestMapping("/timesheet")
public class TimeSheetController {
    private  final TimeSheetService timeSheetService;

    public TimeSheetController(TimeSheetService timeSheetService) {
        this.timeSheetService = timeSheetService;
    }

    @PostMapping("/createTimeSheet")
    public ResponseEntity<?> createTimeSheet(@RequestBody TimeSheet timeSheet){
        TimeSheet createTimeSheet = timeSheetService.createTimeSheet(timeSheet);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getTimeSheetById/{id}")
    public  ResponseEntity<?> getTimeSheetById(@PathVariable String id){
        TimeSheet timeSheet = timeSheetService.getTimeSheetById(id);
        if (timeSheet != null) {
            return ResponseEntity.ok(timeSheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getAllTimeSheet")
    public  ResponseEntity<?> getAllTimeSheet (){
        return ResponseEntity.ok(timeSheetService.getAllTimeSheet());
    }

    @DeleteMapping("/deleteTimeSheeetById/{id}")
    public  ResponseEntity<?> deleteTimeSheeetById(@PathVariable String id){
        boolean deleted = timeSheetService.deleteTimeSheet(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateTimeSheetById/{id}")
    public  ResponseEntity<?> updateTimeSheetById(@PathVariable String id,@RequestBody TimeSheet timeSheet){
        TimeSheet update = timeSheetService.updateTimeSheet(id, timeSheet);
        if(update!= null){
            return  ResponseEntity.ok(update);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getTimeSheetData/{month}/{year}/{autoId}")
    public  ResponseEntity<?> getMonthlyTimeSheetData( @PathVariable int month ,@PathVariable int year, @PathVariable String autoId ){
        ArrayList<TimeSheetDto>  timeSheet = (ArrayList<TimeSheetDto>) timeSheetService.getTimeSheetData(month,year, autoId);
        if (timeSheet != null) {
            return ResponseEntity.ok(timeSheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getTimeSheetWeekData")
    public  ResponseEntity<?> getWeekDataTimeSheet(@RequestParam LocalDate weekStartDate){
        ArrayList<TimeSheetDto>  timeSheet = (ArrayList<TimeSheetDto>) timeSheetService.getWeeklyTimeSheetData(weekStartDate);
        if (timeSheet != null) {
            return ResponseEntity.ok(timeSheet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getTimeSheetBydate/{date}")
    public ResponseEntity<?> getdataFordate(@PathVariable LocalDate date) throws TimesheetException {
        try {
            TimeSheet t = timeSheetService.getTimeSheetBydate(date);
            return new ResponseEntity<>(t, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("This is not found", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/updateTimeSheetByDate")
    public  ResponseEntity<?> getTimeSheetByDate(@RequestParam  LocalDate date,@RequestBody TimeSheet timeSheet) throws TimesheetException {

      try{
          TimeSheet exitingTimeSheet = timeSheetService.updateTimeSheetByDate(date, timeSheet);
          if(exitingTimeSheet!= null){
              return  ResponseEntity.ok(exitingTimeSheet);
          }
          else {
              return ResponseEntity.notFound().build();
          }
      }
      catch (Exception e){
          return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
      }

    }
}
