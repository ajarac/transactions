package com.example.domain

import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

class Transaction(val amount: Double, val timestamp: Long) {

    fun isNotValid(): Boolean {
        val now = Instant.now()
        return isOlderThan60Seconds(now) || isFuture(now)
    }

    private fun isOlderThan60Seconds(now: Instant): Boolean {
        val transactionInstant = Instant.ofEpochMilli(timestamp)
        val diff = transactionInstant.until(now, ChronoUnit.SECONDS)
        return diff > 60
    }

    private fun isFuture(now: Instant): Boolean {
        val transactionInstant = Date(timestamp).toInstant()
        val diff = transactionInstant.until(now, ChronoUnit.SECONDS)
        return diff < 0
    }
}
