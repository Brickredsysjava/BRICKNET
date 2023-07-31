package com.attendanceApiForApp.attendanceApiForApp.util;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class TimeZonesList {
    public Set<String> getTimeZones() {
        Set<String> timeZones = ZoneId.getAvailableZoneIds();

        // Convert time zone identifiers to canonical form
//        Set<String> canonicalTimeZones = new HashSet<>();
//        for (String timeZone : timeZones) {
//            canonicalTimeZones.add(timeZone.replaceAll("/", "_"));
//        }
//
//        return canonicalTimeZones;
        return timeZones;
    }
}
