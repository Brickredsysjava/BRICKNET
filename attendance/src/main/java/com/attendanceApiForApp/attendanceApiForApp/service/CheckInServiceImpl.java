package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckIn;
import com.attendanceApiForApp.attendanceApiForApp.repository.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {


    private final CheckInRepository checkInRepository;

    @Autowired
    public CheckInServiceImpl (CheckInRepository checkInRepository){
        this.checkInRepository = checkInRepository; }

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
   public String findFirstCheckInTime() {
        return checkInRepository.findFirstCheckInTime();
    }
}
