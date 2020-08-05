package com.jacobzmidzinski.dubtram

import com.jacobzmidzinski.dubtram.domain.di.domainModule
import com.jacobzmidzinski.dubtram.domain.models.Direction
import com.jacobzmidzinski.dubtram.domain.services.DirectionsService
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import java.util.*

class DirectionsServiceTests : KoinTest {

    @get:Rule
    val testRule = KoinTestRule.create {
        modules(domainModule)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val directionsService: DirectionsService by inject()

    @Test
    fun getDirections_0000_ReturnsOutbound() {
        val date = Date(0,0,0,0,0)
        assert(directionsService.getCurrentDirection(date) == Direction.Outbound)
    }

    @Test
    fun getDirections_1200_ReturnsOutbound() {
        val date = Date(0,0,0,12,0)
        assert(directionsService.getCurrentDirection(date) == Direction.Outbound)
    }

    @Test
    fun getDirections_1201_ReturnsOutbound() {
        val date = Date(0,0,0,12,1)
        assert(directionsService.getCurrentDirection(date) == Direction.Inbound)
    }

    @Test
    fun getDirections_2359_ReturnsOutbound() {
        val date = Date(0,0,0,23,59)
        assert(directionsService.getCurrentDirection(date) == Direction.Inbound)
    }
}
