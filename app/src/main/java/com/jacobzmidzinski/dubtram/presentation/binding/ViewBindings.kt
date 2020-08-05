package com.jacobzmidzinski.dubtram.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.jacobzmidzinski.dubtram.presentation.models.ViewState
import java.util.*

@BindingAdapter("showOnLoading")
fun View.showOnLoading(viewState: ViewState?) {
    visibility = if (viewState is ViewState.Loading)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("hideOnLoading")
fun View.hideOnLoading(viewState: ViewState?) {
    visibility = if (viewState is ViewState.Loading)
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("hideOnDue")
fun View.hideOnDue(dueMinutes: String?) {
    visibility = if (dueMinutes?.toLowerCase(Locale.getDefault()) == "due")
        View.GONE
    else
        View.VISIBLE
}