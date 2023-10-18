package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.util.TimeZonesList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/timezones")
public class TimeZoneController {

    @GetMapping("/getTimeZone")
    public Set<String> getTimeZones() {
        TimeZonesList timeZoneUtils = new TimeZonesList();
        return timeZoneUtils.getTimeZones();
    }
}
