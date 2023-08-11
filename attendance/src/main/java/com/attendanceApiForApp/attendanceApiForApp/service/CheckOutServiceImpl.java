package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckOut;
import com.attendanceApiForApp.attendanceApiForApp.repository.CheckOutRepository;
import com.attendanceApiForApp.attendanceApiForApp.repository.CustomQuerries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CheckOutServiceImpl implements CheckOutService {

    private final CheckOutRepository checkOutRepository;

    private final CustomQuerries customQuerries;

    @Autowired
    public CheckOutServiceImpl(CheckOutRepository checkOutRepository, CustomQuerries customQuerries) {
        this.checkOutRepository = checkOutRepository;
        this.customQuerries = customQuerries;
    }

    @Override
    public List<CheckOut> getAllCheckOuts() {
        return checkOutRepository.findAll();
    }

    @Override
    public CheckOut getCheckOutById(Long id) {
        return checkOutRepository.findById(id).orElse(null);
    }



    @Override
    public CheckOut createCheckOut(CheckOut checkOut) {
        return checkOutRepository.save(checkOut);
    }



    @Override
    public CheckOut updateCheckOut(Long id, CheckOut checkOut) {
        if (checkOutRepository.existsById(id)) {
            checkOut.setCheckOut_id(id);
            return checkOutRepository.save(checkOut);
        }
        return null;
    }

    @Override
    public boolean deleteCheckOut(Long id) {
        if (checkOutRepository.existsById(id)) {
            checkOutRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<String> findLastCheckOutTimes(Long emp_id, List<String> dates) {
        List<String> result = this.customQuerries.findLastCheckOutTimes(emp_id, dates);
        return result;
    }


}
