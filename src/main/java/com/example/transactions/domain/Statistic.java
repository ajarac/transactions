package com.example.transactions.domain;

import java.util.List;

public class Statistic {

    public final Double sum;
    public final Double avg;
    public final Double max;
    public final Double min;
    public final Integer count;

    private Statistic(Double sum, Double avg, Double max, Double min, Integer count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public static Statistic generate(List<Transaction> transactions) {
        Double sum = 0.0;
        double max = 0.0;
        double min = 0.0;
        for (Transaction transaction : transactions) {
            sum += transaction.amount;
            max = Math.max(transaction.amount, max);
            min = Math.min(transaction.amount, min);
        }
        Integer count = transactions.size();
        double avg;
        if(count == 0) {
            avg = 0.0;
        } else {
            avg = sum / count;
        }

        return new Statistic(sum, avg, max, min, count);
    }

}
