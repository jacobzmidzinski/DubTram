package com.jacobzmidzinski.dubtram.data.repository

import com.jacobzmidzinski.dubtram.data.mapper.TramsForecastDomainDataMapper
import com.jacobzmidzinski.dubtram.data.services.TramsForecastApiService
import com.jacobzmidzinski.dubtram.domain.base.Constants
import com.jacobzmidzinski.dubtram.domain.models.Direction
import com.jacobzmidzinski.dubtram.domain.models.Result
import com.jacobzmidzinski.dubtram.domain.models.TramForecastModel
import com.jacobzmidzinski.dubtram.domain.repository.TramsForecastRepository

class TramsForecastRepositoryImpl(
    private val apiService: TramsForecastApiService,
    private val mapper: TramsForecastDomainDataMapper
) : TramsForecastRepository {

    override suspend fun getTramsForecast(direction: Direction): Result<List<TramForecastModel>> {
        return when(direction) {
            Direction.Inbound -> getInboundTramsForecast()
            Direction.Outbound -> getOutboundTramsForecast()
        }
    }

    private suspend fun getTramsForecast(stopName: String, direction: Direction): Result<List<TramForecastModel>> {
        val tramsForecastDataModel = apiService.getTramsForecast(stopName = stopName)
        val forecastList = mapper.from(tramsForecastDataModel)
        val filteredForecastList = forecastList.filter { it.direction == direction }
        return when(filteredForecastList.size) {
            0 -> Result.Error
            else -> Result.Success(filteredForecastList)
        }
    }

    private suspend fun getInboundTramsForecast(): Result<List<TramForecastModel>> {
        return getTramsForecast(Constants.StopNames.Stillorgan, Direction.Inbound)
    }

    private suspend fun getOutboundTramsForecast(): Result<List<TramForecastModel>> {
        return getTramsForecast(Constants.StopNames.Marlborough, Direction.Outbound)
    }
}