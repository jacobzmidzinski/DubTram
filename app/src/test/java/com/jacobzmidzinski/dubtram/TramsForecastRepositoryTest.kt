package com.jacobzmidzinski.dubtram

import com.jacobzmidzinski.dubtram.data.di.dataModule
import com.jacobzmidzinski.dubtram.data.mapper.TramsForecastDomainDataMapper
import com.jacobzmidzinski.dubtram.data.models.Tram
import com.jacobzmidzinski.dubtram.data.models.TramsForecastDataModel
import com.jacobzmidzinski.dubtram.data.repository.TramsForecastRepositoryImpl
import com.jacobzmidzinski.dubtram.data.services.TramsForecastApiService
import com.jacobzmidzinski.dubtram.domain.di.domainModule
import com.jacobzmidzinski.dubtram.domain.models.Direction
import com.jacobzmidzinski.dubtram.domain.models.Result
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TramsForecastRepositoryTest : KoinTest {

    @get:Rule
    val testRule = KoinTestRule.create {
        MockitoAnnotations.initMocks(this)
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
    fun getTramsForecast_InboundEmptyResponse_ReturnsError() {
        runBlocking {
            val apiService = declareMock<TramsForecastApiService> {
                `when`(this.getTramsForecast(stopName = "Stillorgan"))
                    .thenReturn(TramsForecastDataModel())
            }
            val repository = TramsForecastRepositoryImpl(apiService, mapper)
            val result = repository.getTramsForecast(Direction.Inbound)
            assert(result == Result.Error)
        }
    }

    @Test
    fun getTramsForecast_OutboundEmptyResponse_ReturnsError() {
        runBlocking {
            val apiService = declareMock<TramsForecastApiService> {
                `when`(this.getTramsForecast(stopName = "Marlborough"))
                    .thenReturn(TramsForecastDataModel())
            }
            val repository = TramsForecastRepositoryImpl(apiService, mapper)
            val result = repository.getTramsForecast(Direction.Outbound)
            assert(result == Result.Error)
        }
    }

    @Test
    fun getTramsForecast_OutboundValidResponse_ReturnsNonEmptySuccess() {
        runBlocking {
            val apiService = declareMock<TramsForecastApiService> {
                `when`(this.getTramsForecast(stopName = "Marlborough"))
                    .thenReturn(TramsForecastDataModel(
                        directions = mutableListOf(
                            com.jacobzmidzinski.dubtram.data.models.Direction(
                                "Outbound",
                                trams = mutableListOf(Tram("1", "Dublin"))
                            )
                        )))
            }
            val repository = TramsForecastRepositoryImpl(apiService, mapper)
            val result = repository.getTramsForecast(Direction.Outbound)
            when(result) {
                is Result.Success -> assert(result.data.isNotEmpty())
                else -> assert(false)
            }
            assert(result is Result.Success)
        }
    }

    @Test
    fun getTramsForecast_InboundValidResponse_ReturnsNonEmptySuccess() {
        runBlocking {
            val apiService = declareMock<TramsForecastApiService> {
                `when`(this.getTramsForecast(stopName = "Stillorgan"))
                    .thenReturn(TramsForecastDataModel(
                        directions = mutableListOf(
                            com.jacobzmidzinski.dubtram.data.models.Direction(
                                "Inbound",
                                trams = mutableListOf(Tram("1", "Dublin"))
                            )
                        )))
            }
            val repository = TramsForecastRepositoryImpl(apiService, mapper)
            val result = repository.getTramsForecast(Direction.Inbound)
            when(result) {
                is Result.Success -> assert(result.data.isNotEmpty())
                else -> assert(false)
            }
            assert(result is Result.Success)
        }
    }

    @Test
    fun getTramsForecast_InboundInvalidResponse_ReturnsError() {
        runBlocking {
            val apiService = declareMock<TramsForecastApiService> {
                `when`(this.getTramsForecast(stopName = "Marlborough"))
                    .thenReturn(TramsForecastDataModel(
                        directions = mutableListOf(
                            com.jacobzmidzinski.dubtram.data.models.Direction(
                                "Inbound",
                                trams = mutableListOf(Tram("1", "Dublin"))
                            )
                        )))
            }
            val repository = TramsForecastRepositoryImpl(apiService, mapper)
            val result = repository.getTramsForecast(Direction.Inbound)
            assert(result is Result.Error)
        }
    }

    @Test
    fun getTramsForecast_OutboundInvalidResponse_ReturnsError() {
        runBlocking {
            val apiService = declareMock<TramsForecastApiService> {
                `when`(this.getTramsForecast(stopName = "Stillorgan"))
                    .thenReturn(TramsForecastDataModel(
                        directions = mutableListOf(
                            com.jacobzmidzinski.dubtram.data.models.Direction(
                                "Outbound",
                                trams = mutableListOf(Tram("1", "Dublin"))
                            )
                        )))
            }
            val repository = TramsForecastRepositoryImpl(apiService, mapper)
            val result = repository.getTramsForecast(Direction.Outbound)
            assert(result is Result.Error)
        }
    }
}