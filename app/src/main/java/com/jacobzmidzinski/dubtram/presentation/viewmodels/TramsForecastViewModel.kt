package com.jacobzmidzinski.dubtram.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jacobzmidzinski.dubtram.domain.models.Result
import com.jacobzmidzinski.dubtram.domain.models.TramForecastModel
import com.jacobzmidzinski.dubtram.domain.repository.TramsForecastRepository
import com.jacobzmidzinski.dubtram.domain.services.DirectionsService
import com.jacobzmidzinski.dubtram.presentation.models.ViewState
import com.jacobzmidzinski.dubtram.presentation.models.toViewState

class TramsForecastViewModel(
    private val tramsForecastRepository: TramsForecastRepository,
    private val directionsService: DirectionsService
) : ViewModel() {

    private val viewStateHandler = MutableLiveData<ViewState>().apply {
        value = ViewState.Loading
    }
    val viewState: LiveData<ViewState> = viewStateHandler

    fun fetchTramsForecast() : LiveData<Result<List<TramForecastModel>>> = liveData {
        viewStateHandler.postValue(ViewState.Loading)
        val fetchForecastResult = tramsForecastRepository.getTramsForecast(directionsService.getCurrentDirection())
        viewStateHandler.postValue(fetchForecastResult.toViewState())
        emit(fetchForecastResult)
    }
}