package com.jacobzmidzinski.dubtram

import com.jacobzmidzinski.dubtram.data.di.dataModule
import com.jacobzmidzinski.dubtram.data.mapper.TramsForecastDomainDataMapper
import com.jacobzmidzinski.dubtram.data.models.Direction
import com.jacobzmidzinski.dubtram.data.models.Tram
import com.jacobzmidzinski.dubtram.data.models.TramsForecastDataModel
import com.jacobzmidzinski.dubtram.domain.di.domainModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class TramsForecastDomainDataMapperTests : KoinTest {

    @get:Rule
    val testRule = KoinTestRule.create {
        modules(
            dataModule,
            domainModule
        )
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val mapper : TramsForecastDomainDataMapper by inject()

    @Test
    fun from_emptyDirections_ReturnsEmptyList() {
        val dataModel = TramsForecastDataModel("STOP", "MESSAGE",
            directions = mutableListOf())
        val tramsForecast = mapper.from(dataModel)
        assert(tramsForecast.isEmpty())
    }

    @Test
    fun from_inboundDirection_ReturnsInboundDirectionsList() {
        val dataModel = TramsForecastDataModel("STOP","MESSAGE",
            directions = mutableListOf(
                Direction("Inbound",
                    trams = mutableListOf(
                        Tram("1", "Dublin")
                    )
                )
            )
        )
        val tramsForecast = mapper.from(dataModel)
        assert(tramsForecast.isNotEmpty())
        assert(tramsForecast.all { it.dueMinutes == "1" })
        assert(tramsForecast.all { it.destination == "Dublin" })
        assert(tramsForecast.all { it.direction == com.jacobzmidzinski.dubtram.domain.models.Direction.Outbound })
    }

    @Test
    fun from_outboundDirection_ReturnsOutboundDirectionsList() {
        val dataModel = TramsForecastDataModel("STOP","MESSAGE",
            directions = mutableListOf(
                Direction("Outbound",
                    trams = mutableListOf(
                        Tram("1", "Dublin")
                    )
                )
            )
        )
        val tramsForecast = mapper.from(dataModel)
        assert(tramsForecast.isNotEmpty())
        assert(tramsForecast.all { it.dueMinutes == "1" })
        assert(tramsForecast.all { it.destination == "Dublin" })
        assert(tramsForecast.all { it.direction == com.jacobzmidzinski.dubtram.domain.models.Direction.Outbound })
    }
}