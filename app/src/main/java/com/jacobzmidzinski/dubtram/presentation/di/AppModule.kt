package com.jacobzmidzinski.dubtram.presentation.di

import com.jacobzmidzinski.dubtram.presentation.viewmodels.TramsForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { TramsForecastViewModel(get(), get()) }
}