package com.jacobzmidzinski.dubtram.domain.repository

import com.jacobzmidzinski.dubtram.domain.models.Direction
import com.jacobzmidzinski.dubtram.domain.models.Result
import com.jacobzmidzinski.dubtram.domain.models.TramForecastModel

interface TramsForecastRepository {

    suspend fun getTramsForecast(direction: Direction) : Result<List<TramForecastModel>>
}