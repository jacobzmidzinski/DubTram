package com.jacobzmidzinski.dubtram.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jacobzmidzinski.dubtram.databinding.ItemTramForecastBinding
import com.jacobzmidzinski.dubtram.domain.models.TramForecastModel

class TramsForecastAdapter : ListAdapter<TramForecastModel, TramsForecastAdapter.TramsViewHolder>(TramForecastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TramsViewHolder {
        return TramsViewHolder(ItemTramForecastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TramsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TramsViewHolder(
        private val binding: ItemTramForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tramForecast: TramForecastModel) {
            binding.apply {
                this.tramForecast = tramForecast
            }
        }
    }
}

private class TramForecastDiffCallback : DiffUtil.ItemCallback<TramForecastModel>() {

    override fun areItemsTheSame(oldItem: TramForecastModel, newItem: TramForecastModel): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(
        oldItem: TramForecastModel,
        newItem: TramForecastModel
    ): Boolean {
        return oldItem == newItem
    }
}
