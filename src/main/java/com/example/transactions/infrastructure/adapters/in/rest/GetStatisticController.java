package com.example.transactions.infrastructure.adapters.in.rest;

import com.example.transactions.application.dto.StatisticResponse;
import com.example.transactions.application.use_cases.GetStatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetStatisticController {

    private final GetStatisticService getStatisticService;

    public GetStatisticController(GetStatisticService getStatisticService) {
        this.getStatisticService = getStatisticService;
    }

    @GetMapping(path = "/statistics")
    public ResponseEntity<StatisticResponse> getStatistic() {
        return ResponseEntity.ok(getStatisticService.calculate());
    }
}
