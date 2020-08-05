package com.jacobzmidzinski.dubtram.data.mapper

import com.jacobzmidzinski.dubtram.data.models.TramsForecastDataModel
import com.jacobzmidzinski.dubtram.domain.mapper.Mapper
import com.jacobzmidzinski.dubtram.domain.models.TramForecastModel

interface TramsForecastDomainDataMapper : Mapper<List<TramForecastModel>, TramsForecastDataModel?>