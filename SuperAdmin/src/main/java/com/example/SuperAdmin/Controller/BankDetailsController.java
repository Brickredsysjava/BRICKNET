package com.example.SuperAdmin.Controller;

import com.example.SuperAdmin.DTO.BankDetailsDTO;
import com.example.SuperAdmin.Entity.BankDetails;
import com.example.SuperAdmin.Service.BankDetailsService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@Validated
@RequestMapping("/user/bank")
public class BankDetailsController {
    @Autowired
    private BankDetailsService bankDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addBankDetails")
    public ResponseEntity<BankDetails> addBankDetailsService(@RequestBody @Valid BankDetailsDTO bankDetailsDTO) {
        BankDetails bankDetails = modelMapper.map(bankDetailsDTO, BankDetails.class);
        BankDetails savedBankDetails = bankDetailsService.saveBankdetails(bankDetails);

        if (savedBankDetails != null) {
            return new ResponseEntity<>(savedBankDetails, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addListOfBankDetails")
    public ResponseEntity<List<BankDetails>> addBankDetails(@RequestBody @Valid List<BankDetailsDTO> bankDetailsDTOList) {
        List<BankDetails> bankDetailsList = bankDetailsDTOList.stream()
                .map(dto -> modelMapper.map(dto, BankDetails.class))
                .collect(Collectors.toList());

        List<BankDetails> savedBankDetailsList = bankDetailsService.saveBankDetails(bankDetailsList);

        if (!savedBankDetailsList.isEmpty()) {
            return new ResponseEntity<>(savedBankDetailsList, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateBankDetails/{id}")
    public ResponseEntity<BankDetails> updateBankDetailsById(@PathVariable String id, @RequestBody @Valid BankDetailsDTO bankDetailsDTO) {
        BankDetails bankDetails = modelMapper.map(bankDetailsDTO, BankDetails.class);
        BankDetails updatedBankDetails = bankDetailsService.updateBankDetailsById(id, bankDetails);
        if (updatedBankDetails != null) {
            return new ResponseEntity<>(updatedBankDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/bankDetailsByEmployeeCode")
    public ResponseEntity<BankDetailsDTO> findBankDetailsById(@RequestParam("employeeCode") String employeeCode) {
        BankDetailsDTO bankDetailsDTO = bankDetailsService.getBankDetailsByEmployeeCode(employeeCode);
        if (bankDetailsDTO != null) {
            return new ResponseEntity<>(bankDetailsDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/allBankDetails")
    public ResponseEntity<List<BankDetailsDTO>> getAllBankDetails() {
        List<BankDetailsDTO> allBankDetails = bankDetailsService.getBankDetails();

        if (allBankDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allBankDetails, HttpStatus.OK);
        }
    }
}
