package com.example.application.use_cases

import com.example.application.dto.StatisticResponse
import com.example.application.ports.`in`.GetStatisticUseCase
import com.example.application.ports.out.TransactionStorage
import com.example.domain.Statistic
import com.example.domain.Transaction
import org.springframework.stereotype.Service

@Service
class GetStatisticService(private val transactionStorage: TransactionStorage) : GetStatisticUseCase {

    override fun calculate(): StatisticResponse {
        val transactions: List<Transaction> = transactionStorage.getLastMinutes()
        val statistic: Statistic = Statistic.generate(transactions)
        return StatisticResponse(statistic)
    }
}
