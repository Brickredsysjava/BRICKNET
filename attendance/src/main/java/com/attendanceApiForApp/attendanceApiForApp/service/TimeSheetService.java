package com.attendanceApiForApp.attendanceApiForApp.service;
import com.attendanceApiForApp.attendanceApiForApp.Exception.TimesheetException;
import com.attendanceApiForApp.attendanceApiForApp.dto.TimeSheetDto;
import com.attendanceApiForApp.attendanceApiForApp.model.TimeSheet;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TimeSheetService {
     TimeSheet createTimeSheet(TimeSheet timeSheet);
    TimeSheet getTimeSheetById(String id);
    List<TimeSheet> getAllTimeSheet();
    TimeSheet updateTimeSheet(String id ,TimeSheet timeSheet);
     TimeSheet updateTimeSheetByDate(LocalDate dates, TimeSheet timeSheet) throws TimesheetException;
    boolean deleteTimeSheet(String id);
    public List<TimeSheetDto> getTimeSheetData(int month,int year);
    public List<TimeSheetDto> getWeeklyTimeSheetData(LocalDate weekStartDate);
}
