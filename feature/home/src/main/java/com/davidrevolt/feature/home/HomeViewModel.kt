package com.davidrevolt.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidrevolt.core.data.repository.PointRepository
import com.davidrevolt.core.data.repository.WeatherRepository
import com.davidrevolt.core.data.utils.snackbarmanager.SnackbarManager
import com.davidrevolt.core.model.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import kotlin.text.Typography.degree

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pointRepository: PointRepository,
    private val weatherRepository: WeatherRepository,
    private val snackbarManager: SnackbarManager
) : ViewModel() {


    init {
        viewModelScope.launch { pointRepository.sync() }
    }

    private val _isRefreshing = MutableStateFlow(false)

    val homeUiState = combine(_isRefreshing, pointRepository.getPoints()) { isRefreshing, points ->
        HomeUiState.Data(isRefreshing, points)
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )

    fun createPoint(
        humidity: Int,
        temperature: Int,
        date: Date
    ) {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                pointRepository.create(humidity = humidity, temperature = temperature, date = date)

            } catch (e: Exception) {
                Log.e("AppLog", "${e.message}")
                snackbarManager.snackbarMessage("${e.message}")
            }
            _isRefreshing.value = false
        }
    }

    fun getWeatherByPlace(placeId: String) {
        viewModelScope.launch {
            try{
                val weather = weatherRepository.getWeatherByPlace(placeId)
                snackbarManager.snackbarMessage("The weather in $placeId is ${weather.description} ${weather.temperature}$degree")
            }catch (e:Exception){
                snackbarManager.snackbarMessage("Unable to fetch weather data right now...")
            }
        }
    }
}

sealed interface HomeUiState {
    data class Data(val isSyncing: Boolean, val points: List<Point>) :
        HomeUiState

    data object Loading : HomeUiState
}