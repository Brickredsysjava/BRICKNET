package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;

import java.util.List;

public interface RecordService {
    List<Records> getAllRecords();
    Records getRecordById(Long id);
    Records createRecord(Records record);
    Records updateRecord(Long id, Records record);
    boolean deleteRecord(Long id);
     List<String> getAllDateOfMonth(Long emp_id, int month);
    public Long findLastRecorId(Long emp_id);

}
