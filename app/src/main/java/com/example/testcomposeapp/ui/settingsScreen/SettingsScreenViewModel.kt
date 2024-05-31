package com.example.testcomposeapp.ui.settingsScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcomposeapp.data.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _state = MutableStateFlow("saint_petersburg")
    val state: StateFlow<String> = _state

    init {
        viewModelScope.launch {
            dataStoreManager.getCity().collect {
                _state.value = it
            }
        }
    }

    fun setCity(str: String) {
        viewModelScope.launch { dataStoreManager.setCity(str) }
    }
}