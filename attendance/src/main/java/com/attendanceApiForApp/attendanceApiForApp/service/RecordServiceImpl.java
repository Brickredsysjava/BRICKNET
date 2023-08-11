package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import com.attendanceApiForApp.attendanceApiForApp.repository.CustomQuerries;
import com.attendanceApiForApp.attendanceApiForApp.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public Records getRecordById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    @Override
    public Records createRecord(Records record) {
        return recordRepository.save(record);
    }



    @Override
    public Records updateRecord(Long id, Records records) {
        if (recordRepository.existsById(id)) {
            records.setRecord_id(id);
            return recordRepository.save(records);
        }
        return null;
    }

    @Override
    public boolean deleteRecord(Long id) {
        if (recordRepository.existsById(id)) {
            recordRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllDateOfMonth(Long emp_id, int month) {
        List<Date> dates = customQuerries.getAllDateOfMonth(emp_id, month);
        List<String> dateStrings = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Date date : dates) {
            dateStrings.add(dateFormat.format(date));
        }

        return dateStrings;
    }

    @Override
    public Long findLastRecorId(Long emp_id) {
        Long result = this.customQuerries.findLastRecorId(emp_id);
        return result;
    }


}

