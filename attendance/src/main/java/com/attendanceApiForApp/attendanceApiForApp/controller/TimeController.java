package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.util.TimeFormate;
import com.attendanceApiForApp.attendanceApiForApp.util.TimeZonesList;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/time")
public class TimeController {
    @GetMapping
    public String getCurrentTime(@RequestParam("timeZone") String timeZone) {
        TimeFormate timeFormate = new TimeFormate();
        TimeZonesList timeZonesList = new TimeZonesList();
        Set<String> supportedTimeZones = timeZonesList.getTimeZones();
        if (!supportedTimeZones.contains(timeZone)) {
            return "Invalid time zone";
        }
        return   timeFormate.formatTimeForTimeZone(timeZone);
    }

    @GetMapping("/getTodaysDate")
    public String getTodayDate() {
        LocalDate today = LocalDate.parse(LocalDate.now().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        return today.format(formatter);
    }





}
