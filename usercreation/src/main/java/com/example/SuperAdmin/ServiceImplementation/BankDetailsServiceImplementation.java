package com.example.SuperAdmin.ServiceImplementation;

import com.example.SuperAdmin.Entity.BankDetails;
import com.example.SuperAdmin.Repository.BankDetailsRepository;
import com.example.SuperAdmin.Service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BankDetailsServiceImplementation implements BankDetailsService {


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
        public List<BankDetails> getBankDetails() {
            return bankDetailsRepository.findAll();
        }
        @Override
        public BankDetails getBankDetailsById(Long id) {

            return bankDetailsRepository.findById(id).orElse(null);
        }

    @Override
        public String deleteBankDetails(Long  id) {
           bankDetailsRepository.deleteById(id);
            return "BankDetails removed !! " + id;
        }
        @Override
        public BankDetails updateBankDetails(BankDetails bankDetails) {
            BankDetails existingBankDetails = bankDetailsRepository.findById(bankDetails.getId()).orElse(null);

            return bankDetailsRepository.save(existingBankDetails);
        }
    }


