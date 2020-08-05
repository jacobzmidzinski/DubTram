package com.jacobzmidzinski.dubtram.presentation.models

import com.jacobzmidzinski.dubtram.domain.models.Result

sealed class ViewState {
    object Success : ViewState()
    object Loading : ViewState()
    object Error : ViewState()
}

fun <T> Result<T>.toViewState() : ViewState {
    return when (this) {
        Result.Error -> ViewState.Error
        Result.Loading -> ViewState.Loading
        is Result.Success<T> -> ViewState.Success
    }
}