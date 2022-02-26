package com.example.infrastructure.`in`.rest

import com.example.application.use_cases.GetStatisticService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetStatisticController(private val getStatisticService: GetStatisticService) {

    @GetMapping(path = ["/statistics"])
    fun getStatistic(): ResponseEntity<Any> = ResponseEntity.ok(getStatisticService.calculate())
}
