package com.example.testcomposeapp.ui.settingsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcomposeapp.data.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsScreenViewModel(private val dataStoreManager: DataStoreManager): ViewModel() {

    private val _selectedCity = MutableStateFlow("")
    val selectedCity: StateFlow<String> = _selectedCity

    init {
        viewModelScope.launch {
            dataStoreManager.getCity().collect {
                _selectedCity.value = it
            }
        }
    }

    fun setCity(str: String) {
        viewModelScope.launch { dataStoreManager.setCity(str) }
    }
}