package com.example.weathertestappk.ui.main.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathertestappk.R
import com.example.weathertestappk.databinding.MainFragmentBinding
import com.example.weathertestappk.ui.main.viewmodel.MainViewModel
import com.example.weathertestappk.ui.main.viewmodel.AppState
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private   val binding get() = _binding!!
    private   var _binding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        _binding = MainFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.liveData.observe(viewLifecycleOwner) { state ->
                 renderData(state)
        }

        viewModel.getWeatherFromLocalSource()
    }



    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Loading -> binding.loadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.message.text = "${state.weather.city.name}" +
                        "\n lat/lon ${state.weather.city.lat} ${state.weather.city.lon}" +
                        "\n температура ${state.weather.temperature}" +
                        "\n ощущается ${state.weather.feelsLike}"
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView,"Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Realod") {viewModel.getWeatherFromLocalSource() }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}