package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.Entity.BankDetails;
import com.example.SuperAdmin.ServiceImplementation.BankDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankDetailsController {
    @Autowired
    private BankDetailsServiceImplementation bankDetailsService;

    @PostMapping("/addBankDetails")
    public BankDetails addBankDetailsService(@RequestBody BankDetails bankDetails) {
        return bankDetailsService.saveBankdetails(bankDetails);
    }

    @PostMapping("/addListOfBankDetails")
    public List<BankDetails> addBankDetails(@RequestBody List<BankDetails> bankDetails) {
        return bankDetailsService.saveBankDetails(bankDetails);
    }

    @GetMapping("/allBankDetails")
    public List<BankDetails> findAllBankDetails() {
        return bankDetailsService.getBankDetails();
    }

    @GetMapping("/BankDetailsById/{id}")
    public BankDetails findBankDetailsById(@PathVariable Long id) {
        return bankDetailsService.getBankDetailsById(id);
    }

    @PutMapping("/updateBankDetails")
    public BankDetails updateBankDetails(@RequestBody BankDetails bankDetails) {
        return bankDetailsService.updateBankDetails(bankDetails);
    }

    @DeleteMapping("/deleteBankDetails/{id}")
    public String deleteBankDetails(@PathVariable Long id) {
        return bankDetailsService.deleteBankDetails(id);
    }
}
