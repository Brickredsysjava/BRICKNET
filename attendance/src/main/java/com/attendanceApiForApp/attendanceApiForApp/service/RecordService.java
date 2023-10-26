package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface RecordService {
    List<Records> getAllRecords();
    Records getRecordById(String id);
    Records createRecord(Records record);
    Records updateRecord(String id, Records record);
    boolean deleteRecord(String id);
    public  void postDurationToRecord(String emp_id, String record_id , String durations );
    public String getRecordId(String auto_id, Date todayDate);
    public  void updateDurationInRecord(String durations, String record_id );
    public  void updateStatusInRecord(boolean status, String record_id );
    public  void updateTimeZoneInRecord(String record_id, String timezone );
    public boolean getStatusValue(String record_id );
    public boolean getIsSelecteTimeZone(String record_id );
    public  void updateIsSelectTimeZoneInRecord(boolean time_zone_selected, String record_id );
    public String getTimeZone(String record_id);
}
