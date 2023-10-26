package com.attendanceApiForApp.attendanceApiForApp.service;

import com.attendanceApiForApp.attendanceApiForApp.dto.HistoryDto;
import com.attendanceApiForApp.attendanceApiForApp.dto.TransactionDto;
import com.attendanceApiForApp.attendanceApiForApp.model.Transactions;

import java.util.List;

public interface TransactionsService {
    List<Transactions> getAllTransactions();
    Transactions getTransactionsById(String id);
    Transactions createTransactions(Transactions transactions);
    Transactions updateTransactions(String id, Transactions transactions);
    boolean deleteTransactions(String id);
    public List<HistoryDto> getHistoryData(int months, String auto_id);
    public  List<TransactionDto> getCurrentDetails(String record_id);
    public String getLastTransactionId(String record_id, String emp_id);
    public  void updateCheckout( String checkout_times,String transaction_id );
    public  void updateDurationInTransaction(String durations,String transaction_id );
}
