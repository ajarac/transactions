package com.example.transactions.application.ports.in;

import com.example.transactions.domain.Transaction;

public interface AddTransactionUseCase {

    Boolean add(Transaction transaction);
}
