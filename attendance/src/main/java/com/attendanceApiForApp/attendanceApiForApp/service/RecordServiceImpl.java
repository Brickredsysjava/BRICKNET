package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import com.attendanceApiForApp.attendanceApiForApp.repository.CustomQuerries;
import com.attendanceApiForApp.attendanceApiForApp.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    private final CustomQuerries customQuerries;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository, CustomQuerries customQuerries) {
        this.recordRepository = recordRepository;
        this.customQuerries = customQuerries;
    }

    @Override
    public List<Records> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public Records getRecordById(String id) {
        return recordRepository.findById(id).orElse(null);
    }

    @Override
    public Records createRecord(Records record) {
        return recordRepository.save(record);
    }

    @Override
    public Records updateRecord(String id, Records records) {
        if (recordRepository.existsById(id)) {
            records.setRecord_id(id);
            return recordRepository.save(records);
        }
        return null;
    }

    @Override
    public boolean deleteRecord(String id) {
        if (recordRepository.existsById(id)) {
            recordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void postDurationToRecord(String emp_id, String record_id, String durations) {
        customQuerries.postDurationToRecord(emp_id, record_id, durations);
    }

    @Override
    public String getRecordId(String auto_id, Date todayDate) {
        return customQuerries.getRecordId(auto_id, todayDate);
    }

    @Override
    @Transactional
    public void updateDurationInRecord(String durations, String record_id) {
        customQuerries.updateDurationInRecord(durations, record_id);
    }

    @Override
    @Transactional
    public void updateStatusInRecord(boolean status, String record_id) {
        customQuerries.updateStatusInRecord(status, record_id);
    }

    @Override
    @Transactional
    public void updateTimeZoneInRecord(String record_id, String timezone) {
        customQuerries.updateTimeZoneInRecord(record_id, timezone);
    }

    @Override
    public boolean getStatusValue(String record_id) {
        boolean res = customQuerries.getStatusValue(record_id);
       return  res;
    }

    @Override
    public boolean getIsSelecteTimeZone(String record_id) {
        boolean res = customQuerries.getIsSelecteTimeZone(record_id);
        return res;
    }

    @Override
    public void updateIsSelectTimeZoneInRecord(boolean time_zone_selected, String record_id) {
        customQuerries.updateIsSelectTimeZoneInRecord(time_zone_selected, record_id);
    }

    @Override
    @Transactional
    public String getTimeZone(String record_id) {
        String res = customQuerries.getTimeZone(record_id);
        return res;
    }


}

