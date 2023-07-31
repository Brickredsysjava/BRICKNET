package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.model.CheckOut;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CheckOutService {
    List<CheckOut> getAllCheckOuts();
    CheckOut getCheckOutById(Long id);
    CheckOut createCheckOut(CheckOut checkOut);
    CheckOut updateCheckOut(Long id, CheckOut checkOut);
    boolean deleteCheckOut(Long id);
    String findLasttCheckOutTime();
}

