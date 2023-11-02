package com.attendanceApiForApp.attendanceApiForApp.dto;


import com.attendanceApiForApp.attendanceApiForApp.model.Employee;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@Builder
@Data
public class TimeSheetDto implements Comparable<TimeSheetDto> {

    private List<String> Task;

    private LocalDate dates;

    private String durations;

    private boolean status = false;

    private  String TypeofDay;
    @Override
    public int compareTo(TimeSheetDto other) {
        return this.dates.compareTo(other.dates);
    }

}
