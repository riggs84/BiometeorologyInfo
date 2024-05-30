package com.example.testcomposeapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val ctx: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("compose_app")
    private val CITY = stringPreferencesKey("city")

    suspend fun setCity(city: String) {
        ctx.dataStore.edit { preferences -> preferences[CITY] = city }
    }

    fun getCity(): Flow<String> {
        return ctx.dataStore.data.map {
            it[CITY] ?: "saint_petersburg"
            //return@map "https://world-weather.ru/pogoda/russia/${city}/biometeorology/"
        }
    }

    fun getUrl(): Flow<String> {
        return ctx.dataStore.data.map {
            val city = it[CITY] ?: "saint_petersburg"
            return@map "https://world-weather.ru/pogoda/russia/${city}/biometeorology/"
        }
    }
}