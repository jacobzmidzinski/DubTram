package com.jacobzmidzinski.dubtram.domain.services

import com.jacobzmidzinski.dubtram.domain.models.Direction
import java.util.*

class DirectionsServiceImpl : DirectionsService {

    override fun getCurrentDirection(date: Date): Direction {
        return when (date.hours) {
            in 0..11 -> Direction.Outbound
            in 13..23 -> Direction.Inbound
            else -> when (date.minutes) {
                in 1..59 -> Direction.Inbound
                else -> Direction.Outbound
            }
        }
    }
}