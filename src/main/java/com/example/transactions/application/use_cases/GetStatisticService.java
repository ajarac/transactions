package com.example.transactions.application.use_cases;

import com.example.transactions.application.ports.in.GetStatisticUseCase;
import com.example.transactions.application.ports.out.TransactionStorage;
import com.example.transactions.domain.Statistic;
import com.example.transactions.domain.Transaction;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetStatisticService implements GetStatisticUseCase {

    private final TransactionStorage transactionStorage;

    public GetStatisticService(TransactionStorage transactionStorage) {
        this.transactionStorage = transactionStorage;
    }

    @Override
    public Statistic calculate() {
        List<Transaction> transactions = transactionStorage.getLastMinute();
        return Statistic.generate(transactions);
    }
}
