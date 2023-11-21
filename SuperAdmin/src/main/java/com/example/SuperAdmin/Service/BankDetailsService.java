package com.example.SuperAdmin.Service;

import com.example.SuperAdmin.DTO.BankDetailsDTO;
import com.example.SuperAdmin.Entity.BankDetails;

import java.util.List;
import java.util.UUID;

public interface BankDetailsService {
    BankDetails saveBankdetails(BankDetails bankDetails);

    List<BankDetails> saveBankDetails(List<BankDetails> bankDetails);

    BankDetails updateBankDetailsById(String id,BankDetails bankDetails);
    public BankDetailsDTO getBankDetailsByEmployeeCode(String employeeCode);
    public List<BankDetailsDTO> getBankDetails() ;
}
