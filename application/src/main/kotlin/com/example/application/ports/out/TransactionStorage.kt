package com.example.application.ports.out

import com.example.domain.Transaction

interface TransactionStorage {
    fun getLastMinutes(): List<Transaction>
    fun save(transaction: Transaction)
}
