package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckIn;
import com.attendanceApiForApp.attendanceApiForApp.repository.CheckInRepository;
import com.attendanceApiForApp.attendanceApiForApp.repository.CustomQuerries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {


    private final CheckInRepository checkInRepository;

    private final CustomQuerries customQuerries;

    @Autowired
    public CheckInServiceImpl (CheckInRepository checkInRepository,CustomQuerries customQuerries){
        this.checkInRepository = checkInRepository;
        this.customQuerries = customQuerries;
    }

    @Override
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }

    @Override
    public CheckIn getCheckInById(Long id) {
        return checkInRepository.findById(id).orElse(null);
    }

    @Override
    public CheckIn createCheckIn(CheckIn checkIn) {
        return checkInRepository.save(checkIn);
    }

    @Override
    public CheckIn updateCheckIn(Long id, CheckIn checkIn) {
        if (checkInRepository.existsById(id)) {
            checkIn.setCheckin_id(id);
            return checkInRepository.save(checkIn);
        }
        return null;
    }

    @Override
    public boolean deleteCheckIn(Long id) {
        if (checkInRepository.existsById(id)) {
            checkInRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<String> findFirstCheckInTimes(Long emp_id, List<String> dates) {
        List<String> results = this.customQuerries.findFirstCheckInTimes(emp_id, dates);
        return results;
    }
}
