package com.example.transactions.domain;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Transaction {

    public final Double amount;
    public final Long timestamp;

    public Transaction(Double amount, Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Boolean isNotValid() {
        Instant now = Instant.now();
        return isOlderThan60Seconds(now) || isFuture(now);
    }

    private Boolean isOlderThan60Seconds(Instant now) {
        Instant transactionInstant = Instant.ofEpochMilli(timestamp);
        long diff = transactionInstant.until(now, ChronoUnit.SECONDS);
        return diff > 60;
    }

    private Boolean isFuture(Instant now) {
        Instant transactionInstant = new Date(timestamp).toInstant();
        long diff = transactionInstant.until(now, ChronoUnit.SECONDS);
        return diff < 0;
    }
}
