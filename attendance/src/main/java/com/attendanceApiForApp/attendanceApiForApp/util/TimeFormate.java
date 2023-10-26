package com.attendanceApiForApp.attendanceApiForApp.util;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeFormate {

    public String formatTimeForTimeZone(String timeZone) {
        // Get the current time in the selected time zone
        LocalTime currentTime = LocalTime.now(ZoneId.of(timeZone));

        // Formatting LocalTime to a custom format
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return currentTime.format(customFormatter);
    }
}
