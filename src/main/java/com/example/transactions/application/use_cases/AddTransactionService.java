package com.example.transactions.application.use_cases;

import com.example.transactions.application.ports.in.AddTransactionUseCase;
import com.example.transactions.application.ports.out.TransactionStorage;
import com.example.transactions.domain.Transaction;
import org.springframework.stereotype.Service;

@Service
public class AddTransactionService implements AddTransactionUseCase {

    private final TransactionStorage transactionStorage;

    public AddTransactionService(TransactionStorage transactionStorage) {
        this.transactionStorage = transactionStorage;
    }

    @Override
    public Boolean add(Transaction transaction) {
        if (transaction.isNotValid()) {
            return false;
        }
        transactionStorage.save(transaction);
        return true;
    }
}
