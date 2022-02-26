package com.example.infrastructure.out.in_memory

import com.example.application.ports.out.TransactionStorage
import com.example.domain.Transaction
import org.springframework.stereotype.Repository
import java.text.SimpleDateFormat
import java.util.*

@Repository
class InMemoryTransactionStorage : TransactionStorage {
    val map: HashMap<String, ArrayList<Transaction>> = HashMap()

    companion object {
        private fun getFormattedDate(date: Date): String {
            return SimpleDateFormat("yyyyMMddHHmm").format(date)
        }
    }

    @Synchronized
    override fun getLastMinutes(): List<Transaction> {
        val now = Date()
        val oneMinuteAgo = Date(System.currentTimeMillis() - 60 * 1000)
        val transactions: ArrayList<Transaction> = ArrayList()
        val listTimes = listOf(getFormattedDate(now), getFormattedDate(oneMinuteAgo))
        listTimes.forEach { transactions.addAll(map.getOrDefault(it, ArrayList())) }
        removeOlder(listTimes)
        return getTransactionsLastMinute(transactions, oneMinuteAgo)
    }

    @Synchronized
    override fun save(transaction: Transaction) {
        val formattedDate = getFormattedDate(Date(transaction.timestamp))
        val transactions = if (map.containsKey(formattedDate)) {
            map[formattedDate]!!
        } else {
            ArrayList()
        }
        transactions.add(transaction)
        map[formattedDate] = transactions
    }

    private fun getTransactionsLastMinute(
        transactions: ArrayList<Transaction>,
        oneMinuteAgo: Date
    ): List<Transaction> {
        return transactions.filter { transaction: Transaction -> transaction.timestamp > oneMinuteAgo.time }
    }

    private fun removeOlder(listTimes: List<String>) {
        map.entries.removeIf { (key): Map.Entry<String, ArrayList<Transaction>> ->
            !listTimes.contains(
                key
            )
        }
    }
}
