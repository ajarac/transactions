package com.example.transactions.infrastructure.adapters.in.rest;

import com.example.transactions.application.use_cases.AddTransactionService;
import com.example.transactions.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AddTransactionController {

    private final AddTransactionService addTransactionService;

    public AddTransactionController(AddTransactionService addTransactionService) {
        this.addTransactionService = addTransactionService;
    }

    @PostMapping(path = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction) {
        log.info("Transaction entry amount: {}  timestamp:{}", transaction.amount, transaction.timestamp);
        Boolean processed = addTransactionService.add(transaction);
        return getResponseEntity(processed);
    }

    private ResponseEntity<Void> getResponseEntity(Boolean processed) {
        if (processed) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

