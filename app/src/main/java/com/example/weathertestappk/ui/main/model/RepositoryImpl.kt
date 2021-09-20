package com.example.weathertestappk.ui.main.model

import com.example.weathertestappk.ui.main.model.Repository

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather = Weather()
    override fun getWeatherFromLocalStorage(): Weather = Weather()
}