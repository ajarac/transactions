package com.example.transactions.application.ports.in;

import com.example.transactions.domain.Statistic;

public interface GetStatisticUseCase {
    Statistic calculate();
}