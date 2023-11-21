package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.DTO.BankDetailsDTO;
import com.example.SuperAdmin.Entity.BankDetails;
import com.example.SuperAdmin.Repository.BankDetailsRepository;
import com.example.SuperAdmin.Repository.CustomQuery;
import com.example.SuperAdmin.Service.BankDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BankDetailsServiceImplementation implements BankDetailsService {

    private CustomQuery customQuery;
    public BankDetailsServiceImplementation(CustomQuery customQuery) {
        this.customQuery = customQuery;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Override
    public BankDetails saveBankdetails(BankDetails bankDetails) {
        return bankDetailsRepository.save(bankDetails);
    }

    @Override
    public List<BankDetails> saveBankDetails(List<BankDetails> bankDetails) {
        return bankDetailsRepository.saveAll(bankDetails);
    }

    @Override
    public BankDetailsDTO getBankDetailsByEmployeeCode(String employeeCode) {
        return customQuery.getBankDetailsByEmployeeCode(employeeCode);
    }
    @Override
    public List<BankDetailsDTO> getBankDetails() {
        List<BankDetails> bankDetailsList = bankDetailsRepository.findAll();
        return bankDetailsList.stream()
                .map(bankDetails -> modelMapper.map(bankDetails, BankDetailsDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public BankDetails updateBankDetailsById(String id, BankDetails bankDetails) {
        BankDetails existingBankDetails = bankDetailsRepository.findById(id).orElse(null);

        if (existingBankDetails != null) {
            existingBankDetails.setAccountNumber(bankDetails.getAccountNumber());
            existingBankDetails.setBankName(bankDetails.getBankName());
            existingBankDetails.setBranchName(bankDetails.getBranchName());
            existingBankDetails.setPanNumber(bankDetails.getPanNumber());
            existingBankDetails.setIfscCode(bankDetails.getIfscCode());

            return bankDetailsRepository.save(existingBankDetails);
        } else {
            return null;
        }
    }
}
