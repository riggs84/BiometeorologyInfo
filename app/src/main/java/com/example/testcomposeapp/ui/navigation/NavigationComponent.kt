package com.example.testcomposeapp.ui.navigation

import HomeScreen
import SettingsScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

const val HOME = "home"
const val SETTINGS = "settings"

@Composable
fun NavigationHost(modifier: Modifier = Modifier) {
    val navigationController = rememberNavController()
    var selectedState by remember { mutableStateOf(HOME) }

    NavHost(
        navController = navigationController,
        startDestination = HOME,
        modifier = modifier
    ) {
        composable(HOME) { HomeScreen() }
        composable(SETTINGS) { SettingsScreen() }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (selectedState != HOME) Icons.Outlined.Home else Icons.Default.Home,
            contentDescription = "home screen navigation",
            Modifier
                .size(48.dp)
                .clickable {
                    navigationController.navigate(HOME)
                    selectedState = HOME
                })
        Icon(
            imageVector = if (selectedState != SETTINGS) Icons.Outlined.Settings else Icons.Default.Settings,
            contentDescription = "settings screen navigation",
            modifier = Modifier
                .size(48.dp)
                .clickable {
                    navigationController.navigate(HOME)
                    selectedState = SETTINGS
                })

    }
}