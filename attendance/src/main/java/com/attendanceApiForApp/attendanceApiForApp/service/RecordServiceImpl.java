package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.Records;
import com.attendanceApiForApp.attendanceApiForApp.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
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

//    @Override
//    public List<Records> findByEmployeeAndMonth(Long empId, int month) {
//        return recordRepository.findByEmployeeAndMonth(empId, month);
//    }
}

