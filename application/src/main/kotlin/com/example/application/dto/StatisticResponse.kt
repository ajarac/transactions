package com.example.application.dto

import com.example.domain.Statistic

data class StatisticResponse(val sum: Double, val avg: Double, val max: Double, val min: Double, val count: Int) {
    companion object {
        operator fun invoke(statistic: Statistic): StatisticResponse {
            return StatisticResponse(statistic.sum, statistic.avg, statistic.max, statistic.min, statistic.count)
        }
    }
}
