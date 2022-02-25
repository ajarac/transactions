package com.example.transactions.application.dto;

import com.example.transactions.domain.Statistic;
import lombok.Value;

@Value
public class StatisticResponse {
    public Double sum;
    public Double avg;
    public Double max;
    public Double min;
    public Integer count;

    public static StatisticResponse from(Statistic statistic) {
        return new StatisticResponse(statistic.sum, statistic.avg, statistic.max, statistic.min, statistic.count);
    }
}
