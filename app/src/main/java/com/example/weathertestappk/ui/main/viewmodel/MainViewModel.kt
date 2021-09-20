package com.example.weathertestappk.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weathertestappk.ui.main.model.Repository
import com.example.weathertestappk.ui.main.model.RepositoryImpl

class MainViewModel : ViewModel() {


    private val repository: Repository = RepositoryImpl()
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    val liveData: LiveData<AppState> = liveDataToObserve

    fun getWeatherFromLocalSource() = getDataFromLocalSource()
    fun getWeatherFromRemoteSource() = getDataFromLocalSource()


    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(5000)
            liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))

        }.start()
    }


}