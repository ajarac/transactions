package com.example.application.use_cases

import com.example.application.ports.`in`.AddTransactionUseCase
import com.example.application.ports.out.TransactionStorage
import com.example.domain.Transaction
import org.springframework.stereotype.Service

@Service
class AddTransactionService(transactionStorage: TransactionStorage) :
    AddTransactionUseCase {
    private val transactionStorage: TransactionStorage

    init {
        this.transactionStorage = transactionStorage
    }

    override fun add(transaction: Transaction): Boolean {
        if (transaction.isNotValid()) {
            return false
        }
        transactionStorage.save(transaction)
        return true
    }
}
