package com.example.weathertestappk.ui.main.viewmodel

import com.example.weathertestappk.ui.main.model.Weather

sealed class AppState {

    data class Success(val weather: Weather): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading : AppState()
}