package com.example.testcomposeapp.ui.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcomposeapp.data.Forcast
import com.example.testcomposeapp.data.repository.HtmlParserImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {

    private var parserRepository: HtmlParserImpl = HtmlParserImpl()

    private var mutableState = MutableStateFlow<ViewState>(ViewState.Loading)
    val state: StateFlow<ViewState> = mutableState

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = parserRepository.getTodayForcast()
                mutableState.value = ViewState.Success(result)
            } catch(e: Exception) {
               mutableState.value = ViewState.Error
            }

        }
    }
}

sealed class ViewState {
    data class Success(val data: Forcast): ViewState()
    data object Loading: ViewState()
    data object Error: ViewState()
}