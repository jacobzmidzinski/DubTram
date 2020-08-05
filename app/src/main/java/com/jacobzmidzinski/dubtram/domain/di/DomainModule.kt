package com.jacobzmidzinski.dubtram.domain.di

import com.jacobzmidzinski.dubtram.domain.services.DirectionsService
import com.jacobzmidzinski.dubtram.domain.services.DirectionsServiceImpl
import org.koin.dsl.module

val domainModule = module {

    factory<DirectionsService> { DirectionsServiceImpl() }
}