package com.example.testcomposeapp.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcomposeapp.data.Forecast
import com.example.testcomposeapp.data.repository.HtmlParserImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {

    private var parserRepository: HtmlParserImpl = HtmlParserImpl()

    private var mutableState = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = mutableState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = parserRepository.getTodayForecast()
                mutableState.update { ViewState.Success(result) }
            } catch(e: Exception) {
                mutableState.update { ViewState.Error(e.message ?: "exception happen") }
            }

        }
    }
}

sealed class ViewState {
    data class Success(val data: Forecast): ViewState()
    data object Loading: ViewState()
    data class Error(val err: String) : ViewState()
}