package com.jacobzmidzinski.dubtram.data.services

import com.jacobzmidzinski.dubtram.data.models.TramsForecastDataModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TramsForecastApiService {

    @GET("get.ashx")
    @Headers("Accept: application/xml")
    suspend fun getTramsForecast(@Query("action") query: String = "forecast",
                                 @Query("encrypt") encrypt: Boolean = false,
                                 @Query("stop") stopName: String): TramsForecastDataModel

}