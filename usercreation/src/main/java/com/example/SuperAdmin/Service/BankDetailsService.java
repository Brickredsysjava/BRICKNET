package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.Entity.BankDetails;

import java.util.List;

public interface BankDetailsService {
    BankDetails saveBankdetails(BankDetails bankDetails);

    List<BankDetails> saveBankDetails(List<BankDetails> bankDetails);

    List<BankDetails> getBankDetails();

    BankDetails getBankDetailsById(Long id);

    //    public User getEmployeeByName(String name) {
//        return userRepository.findByName(name);
//    }
    String deleteBankDetails(Long id);

    BankDetails updateBankDetails(BankDetails bankDetails);
}
