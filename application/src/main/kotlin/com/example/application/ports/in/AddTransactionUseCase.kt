package com.example.application.ports.`in`

import com.example.domain.Transaction

interface AddTransactionUseCase {
    fun add(transaction: Transaction): Boolean
}