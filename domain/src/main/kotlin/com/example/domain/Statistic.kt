package com.example.domain

class Statistic private constructor(val sum: Double, val avg: Double, val max: Double, val min: Double, val count: Int) {
    companion object {
        fun generate(transactions: List<Transaction?>): Statistic {
            var sum = 0.0
            var max = 0.0
            var min = 0.0
            for (transaction in transactions) if (transaction != null) {
                run {
                    sum += transaction.amount
                    max = Math.max(transaction.amount, max)
                    min = Math.min(transaction.amount, min)
                }
            }
            val count = transactions.size
            val avg: Double = if (count == 0) {
                0.0
            } else {
                sum / count
            }
            return Statistic(sum, avg, max, min, count)
        }
    }
}