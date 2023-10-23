package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.dto.HistoryDto;
import com.attendanceApiForApp.attendanceApiForApp.dto.TransactionDto;
import com.attendanceApiForApp.attendanceApiForApp.model.Transactions;
import com.attendanceApiForApp.attendanceApiForApp.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    private  final TransactionsService transactionsService;

     @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }


    @GetMapping("/getAllTransactons")
    public ResponseEntity<?>getAllTrnasaction(){
         return  ResponseEntity.ok(transactionsService.getAllTransactions());
    }
    @GetMapping("/getTransactionsById/{id}")
    public  ResponseEntity<?>getTransactionById(@PathVariable String id ){
        Transactions transactions = transactionsService.getTransactionsById(id);
        if(transactions != null){
            return  ResponseEntity.ok(transactions);
        }
        else {
            return  ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/createTransactions")
    public  ResponseEntity<?> createTransactions(@RequestBody Transactions transactions){
         Transactions createTransactions = transactionsService.createTransactions(transactions);
         return  ResponseEntity.ok(createTransactions);
    }
    @GetMapping ("/getHistoryData/{months}/{auto_id}")
    public ResponseEntity<List<HistoryDto>>getHistory(
            @PathVariable int months,
            @PathVariable String auto_id){
        List<HistoryDto> history =  transactionsService.getHistoryData( months, auto_id);
        if(history!= null){
            return  ResponseEntity.ok(history);
        }
        else {
            return  ResponseEntity.notFound().build();
        }
     }

    @GetMapping("/getCurrentDetails/{recordId}")
    public ResponseEntity<List<TransactionDto>> getCurrentDetails(@PathVariable String recordId) {
        List<TransactionDto> currentDetails = transactionsService.getCurrentDetails(recordId);
        return new ResponseEntity<>(currentDetails, HttpStatus.OK);
    }

    @GetMapping("/getLastTransactionId/{recordId}/{empId}")
    public ResponseEntity<String> getLastTransactionId(@PathVariable String recordId, @PathVariable String empId) {
        String lastTransactionId = transactionsService.getLastTransactionId(recordId, empId);
        return ResponseEntity.ok(lastTransactionId);
    }

    @PutMapping("/updateCheckout/{transactionId}/{checkOut_Times}")
    public ResponseEntity<String> updateCheckout(
            @PathVariable String transactionId,
            @PathVariable String checkOut_Times
    ) {
        transactionsService.updateCheckout( checkOut_Times, transactionId);
        return ResponseEntity.ok("Checkout updated successfully.");
    }

    @PutMapping("/updateDurationInTransaction/{transactionId}/{durations}")
    public ResponseEntity<String> updateDurationInTransaction(
            @PathVariable String transactionId,
            @PathVariable String durations
    ) {
        transactionsService.updateDurationInTransaction(durations, transactionId);
        return ResponseEntity.ok("Duration in transaction updated successfully.");
    }

}
