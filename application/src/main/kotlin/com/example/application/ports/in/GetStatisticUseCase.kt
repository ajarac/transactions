package com.example.application.ports.`in`

import com.example.application.dto.StatisticResponse

interface GetStatisticUseCase {
    fun calculate(): StatisticResponse
}