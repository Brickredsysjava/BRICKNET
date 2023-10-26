package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.dto.HistoryDto;
import com.attendanceApiForApp.attendanceApiForApp.dto.TransactionDto;
import com.attendanceApiForApp.attendanceApiForApp.model.Transactions;
import com.attendanceApiForApp.attendanceApiForApp.repository.CustomQuerries;
import com.attendanceApiForApp.attendanceApiForApp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private  final TransactionRepository transactionRepository;
    private final CustomQuerries customQuerries;


    public TransactionsServiceImpl(TransactionRepository transactionRepository, CustomQuerries customQuerries) {
        this.transactionRepository = transactionRepository;
        this.customQuerries = customQuerries;
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transactions getTransactionsById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transactions createTransactions(Transactions transactions) {
        return transactionRepository.save(transactions);
    }

    @Override
    public Transactions updateTransactions(String id, Transactions transactions) {
       if(transactionRepository.existsById(id)){
           transactions.setTransaction_id(id);
           return transactionRepository.save(transactions);
       }
        return null;
    }
    @Override
    public boolean deleteTransactions(String id) {
        if(transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<HistoryDto> getHistoryData(int months, String auto_id) {
        return customQuerries.getHistoryData(months, auto_id);
    }

    @Override
    public List<TransactionDto> getCurrentDetails(String record_id) {
        return customQuerries.getCurrentDetails(record_id);
    }

    @Override
    public String getLastTransactionId(String record_id, String emp_id) {
        return customQuerries.getLastTransactionId(record_id, emp_id);
    }

    @Override
    public void updateCheckout( String checkout_times, String transaction_id) {
        customQuerries.updateCheckout(checkout_times, transaction_id);
    }



    @Override
    public void updateDurationInTransaction(String durations, String transaction_id) {
        customQuerries.updateDurationInTransaction(durations, transaction_id);
    }

}
