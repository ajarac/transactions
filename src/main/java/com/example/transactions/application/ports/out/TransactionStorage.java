package com.example.transactions.application.ports.out;

import com.example.transactions.domain.Transaction;
import java.util.List;

public interface TransactionStorage {
    List<Transaction> getLastMinute();
    void save(Transaction transaction);
}
