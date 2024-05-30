package com.example.testcomposeapp.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcomposeapp.data.DataStoreManager
import com.example.testcomposeapp.data.ForecastData
import com.example.testcomposeapp.data.HtmlParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val parser: HtmlParser
) : ViewModel() {

    private lateinit var url: String

    private var mutableState = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = mutableState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                dataStoreManager.getCity().collect {
                    url = it
                }
                val result = parser.getTodayForecast(url)
                mutableState.update { ViewState.Success(result) }
            } catch (e: Exception) {
                mutableState.update { ViewState.Error(e.message ?: "exception happen") }
            }
        }
    }
}

sealed class ViewState {
    data class Success(val data: ForecastData) : ViewState()
    data object Loading : ViewState()
    data class Error(val err: String) : ViewState()
}