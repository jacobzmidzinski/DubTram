package com.jacobzmidzinski.dubtram.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.jacobzmidzinski.dubtram.databinding.FragmentTramsForecastBinding
import com.jacobzmidzinski.dubtram.domain.models.Result
import com.jacobzmidzinski.dubtram.domain.models.TramForecastModel
import com.jacobzmidzinski.dubtram.presentation.viewmodels.TramsForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TramsForecastFragment : Fragment() {

    private val viewModel: TramsForecastViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTramsForecastBinding.inflate(inflater, container, false)
        context ?: binding.root

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TramsForecastAdapter()
        binding.tramsForecastList.adapter = adapter

        binding.refreshFab.setOnClickListener {
            subscribeUi(adapter)
        }

        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: TramsForecastAdapter) {
        val observer = Observer<Result<List<TramForecastModel>>> { result ->
            when (result) {
                is Result.Success -> adapter.submitList(result.data)
            }
        }

        viewModel.fetchTramsForecast().observe(viewLifecycleOwner, observer)
    }
}