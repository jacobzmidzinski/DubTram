package com.jacobzmidzinski.dubtram.data.di

import com.jacobzmidzinski.dubtram.data.mapper.TramsForecastDomainDataMapper
import com.jacobzmidzinski.dubtram.data.mapper.TramsForecastDomainDataMapperImpl
import com.jacobzmidzinski.dubtram.data.repository.TramsForecastRepositoryImpl
import com.jacobzmidzinski.dubtram.data.services.TramsForecastApiService
import com.jacobzmidzinski.dubtram.domain.base.Constants
import com.jacobzmidzinski.dubtram.domain.repository.TramsForecastRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

val dataModule = module {

    single { createRetrofit() }
    single { createTramsForecastApiService(get()) }
    single<TramsForecastRepository> { TramsForecastRepositoryImpl(get(), get()) }
    factory<TramsForecastDomainDataMapper> { TramsForecastDomainDataMapperImpl() }
}

private fun createRetrofit(): Retrofit {

    return Retrofit.Builder()
        .baseUrl(Constants.Api.BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(
            SimpleXmlConverterFactory.createNonStrict(
                Persister(
                    AnnotationStrategy()
                )
            )
        )
        .build()
}

private fun createTramsForecastApiService(retrofit: Retrofit): TramsForecastApiService {
    return retrofit.create(TramsForecastApiService::class.java)
}