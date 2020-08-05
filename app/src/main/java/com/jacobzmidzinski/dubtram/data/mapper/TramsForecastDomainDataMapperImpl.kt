package com.jacobzmidzinski.dubtram.data.mapper

import com.jacobzmidzinski.dubtram.data.models.TramsForecastDataModel
import com.jacobzmidzinski.dubtram.domain.models.Direction
import com.jacobzmidzinski.dubtram.domain.models.TramForecastModel

class TramsForecastDomainDataMapperImpl : TramsForecastDomainDataMapper {

    override fun from(e: TramsForecastDataModel?): List<TramForecastModel> {
        return when (e) {
            null -> emptyList()
            else -> e.let { dataModel ->
                dataModel.directions.flatMap { direction ->
                    direction.trams.map { tram ->
                        TramForecastModel(
                            destination = tram.destination,
                            direction = Direction.valueOf(direction.name),
                            dueMinutes = tram.dueMins)
                    }
                }
            }
        }
    }
}