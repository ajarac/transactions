package com.example.infrastructure.`in`.rest

import com.example.application.use_cases.AddTransactionService
import com.example.domain.Transaction
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AddTransactionController(addTransactionService: AddTransactionService) {
    private val addTransactionService: AddTransactionService

    init {
        this.addTransactionService = addTransactionService
    }

    @PostMapping(path = ["/transactions"], consumes = [APPLICATION_JSON_VALUE])
    fun addTransaction(@RequestBody transaction: Transaction): ResponseEntity<Void> {
        val processed: Boolean = addTransactionService.add(transaction)
        return getResponseEntity(processed)
    }

    private fun getResponseEntity(processed: Boolean): ResponseEntity<Void> = if (processed) {
        ResponseEntity.accepted().build()
    } else {
        ResponseEntity.noContent().build()
    }
}

