package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.Exception.TimesheetException;
import com.attendanceApiForApp.attendanceApiForApp.dto.TimeSheetDto;
import com.attendanceApiForApp.attendanceApiForApp.model.TimeSheet;
import com.attendanceApiForApp.attendanceApiForApp.repository.CustomQuerries;
import com.attendanceApiForApp.attendanceApiForApp.repository.TimeSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.TemporalAdjusters;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TimeSheetServiceImpl implements TimeSheetService {
private  final TimeSheetRepository timeSheetRepository;
    private final CustomQuerries customQuerries;
     @Autowired
    public TimeSheetServiceImpl(TimeSheetRepository timeSheetRepository, CustomQuerries customQuerries) {
        this.timeSheetRepository = timeSheetRepository;
         this.customQuerries = customQuerries;
     }

    @Override
    public TimeSheet  createTimeSheet(TimeSheet timeSheet){
         return timeSheetRepository.save(timeSheet);
    }

    @Override
    public TimeSheet getTimeSheetById(String id) {
        return timeSheetRepository.findById(id).orElse(null);
    }

    @Override
    public List<TimeSheet> getAllTimeSheet() {
        return timeSheetRepository.findAll();
    }

    @Override
    public TimeSheet updateTimeSheet(String id, TimeSheet timeSheet) {
         if(timeSheetRepository.existsById(id)){
             timeSheet.setTimesheet_id(id);
             return timeSheetRepository.save(timeSheet);
         }
        return null;
    }

    @Override
    public TimeSheet updateTimeSheetByDate(LocalDate dates, TimeSheet timeSheet) throws TimesheetException {
         TimeSheet  t1 = timeSheetRepository.findByDates(dates);
        if(t1 != null){
            t1.setDurations(timeSheet.getDurations());
            t1.setTask(timeSheet.getTask());
            t1.setTypeofDay(timeSheet.getTypeofDay());
            System.out.println(t1);
            return timeSheetRepository.save(t1);
        }
        else
           throw  new TimesheetException("TimeSheet Does not Exits on this particular Date");
    }


    @Override
    public boolean deleteTimeSheet(String id) {
         if(timeSheetRepository.existsById(id)){
             timeSheetRepository.deleteById(id);
             return true;
         }
        return false;
    }

    @Override
    public List<TimeSheetDto> getTimeSheetData(int month,int year , String autoId) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);
        List<TimeSheet> data =timeSheetRepository.findAll().stream().filter(i->!i.getDates().isBefore(startOfMonth)&& !i.getDates().isAfter(endOfMonth) && i.getEmployee().getAuto_id().equals(autoId)
        ).collect(Collectors.toList());
    List<TimeSheetDto>newData=  data.stream()
                .map(e ->  TimeSheetDto.builder()
                        .Task(e.getTask())
                        .dates(e.getDates())
                        .durations(e.getDurations())
                        .TypeofDay(e.getTypeofDay())
                        .build())
                .collect(Collectors.toList());
        System.out.println(newData);
        return newData;
    }

     @Override
    public List<TimeSheetDto> getWeeklyTimeSheetData(LocalDate weekStartDate) {
         LocalDate weekEndDate = weekStartDate.plusDays(5).with(TemporalAdjusters.ofDateAdjuster(
                 temporal -> temporal.plusDays(1)
         ));
        LocalDate startOfMonth = weekStartDate.withDayOfMonth(1);
        LocalDate endOfMonth = startOfMonth.plusMonths(1).minusDays(1);

        List<TimeSheet> data = timeSheetRepository.findAll().stream()
                .filter(entry -> !entry.getDates().isBefore(startOfMonth) &&
                        !entry.getDates().isAfter(endOfMonth))
                .collect(Collectors.toList());

        List<TimeSheetDto> weeklyData = data.stream()
                .filter(entry -> !entry.getDates().isBefore(weekStartDate) &&
                        !entry.getDates().isAfter(weekEndDate))
                .map(entry -> TimeSheetDto.builder()
                        .Task(entry.getTask())
                        .dates(entry.getDates())
                        .durations(entry.getDurations())
                        .build())
                .collect(Collectors.toList());

         weeklyData.sort(Comparator.comparing(TimeSheetDto::getDates));

         System.out.println(weeklyData);

        return weeklyData;
    }

    @Override
    public TimeSheet getTimeSheetBydate(LocalDate dates) throws TimesheetException{
        TimeSheet t = timeSheetRepository.findByDates(dates);
        t.getTask();
        t.getDurations();
        return t;
    }


}
