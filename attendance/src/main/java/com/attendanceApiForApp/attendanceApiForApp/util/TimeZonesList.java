package com.attendanceApiForApp.attendanceApiForApp.util;

import java.time.ZoneId;
import java.util.Set;

public class TimeZonesList {
    public Set<String> getTimeZones() {
        Set<String> timeZones = ZoneId.getAvailableZoneIds();
        return timeZones;

    }
}
