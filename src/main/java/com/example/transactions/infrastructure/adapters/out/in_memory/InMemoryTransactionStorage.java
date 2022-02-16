package com.example.transactions.infrastructure.adapters.out.in_memory;

import com.example.transactions.application.ports.out.TransactionStorage;
import com.example.transactions.domain.Transaction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTransactionStorage implements TransactionStorage {

    HashMap<String, ArrayList<Transaction>> map = new HashMap<>();

    private static String getFormattedDate(Date date) {
        return new SimpleDateFormat("yyyyMMddHHmm").format(date);
    }

    @Override
    public synchronized List<Transaction> getLastMinute() {
        var now = new Date();
        var oneMinuteAgo = new Date(System.currentTimeMillis() - 60 * 1000);
        var transactions = new ArrayList<Transaction>();
        var listTimes = List.of(getFormattedDate(now), getFormattedDate(oneMinuteAgo));
        listTimes.forEach(time -> transactions.addAll(map.getOrDefault(time, new ArrayList<>())));
        removeOlder(listTimes);
        return getTransactionsLastMinute(transactions, oneMinuteAgo);
    }

    private List<Transaction> getTransactionsLastMinute(ArrayList<Transaction> transactions, Date oneMinuteAgo) {
        return transactions.stream().filter(transaction -> transaction.timestamp > oneMinuteAgo.getTime()).collect(Collectors.toList());
    }

    @Override
    public synchronized void save(Transaction transaction) {
        String formattedDate = getFormattedDate(new Date(transaction.timestamp));
        ArrayList<Transaction> transactions;
        if (map.containsKey(formattedDate)) {
            transactions = map.get(formattedDate);
        } else {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
        map.put(formattedDate, transactions);
    }

    private void removeOlder(List<String> listTimes) {
        map.entrySet().removeIf(entry -> !listTimes.contains(entry.getKey()));
    }

}
