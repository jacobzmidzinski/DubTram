package com.jacobzmidzinski.dubtram.domain.models

data class TramForecastModel(
    val direction: Direction,
    val dueMinutes: String,
    val destination: String
)