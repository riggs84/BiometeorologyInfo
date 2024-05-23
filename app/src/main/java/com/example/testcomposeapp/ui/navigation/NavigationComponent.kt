package com.example.testcomposeapp.ui.navigation

import HomeScreen
import SettingsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.HOME.path,
        modifier = modifier
    ) {
        composable(Routes.HOME.path) { HomeScreen() }
        composable(Routes.SETTINGS.path) { SettingsScreen() }
    }
}

enum class Routes(val path: String) {
    HOME("home"),
    SETTINGS("settings")
}