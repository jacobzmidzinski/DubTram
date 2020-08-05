package com.jacobzmidzinski.dubtram.domain.services

import com.jacobzmidzinski.dubtram.domain.models.Direction
import java.util.*

interface DirectionsService {

    fun getCurrentDirection(date: Date = Date()) : Direction
}