package com.example.testcomposeapp.ui.navigation

import HomeScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

const val HOME = "home"
const val SETTINGS = "settings"
@Composable
fun NavigationHost(modifier: Modifier = Modifier) {
    val navigationController = rememberNavController()

    NavHost(
        navController = navigationController,
        startDestination = HOME,
        modifier = modifier
    ) {
        composable(HOME) { HomeScreen() }
        composable(SETTINGS) {  }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Home Screen",
            fontSize = 24.sp,
            modifier = Modifier.clickable { navigationController.navigate(HOME) })
        Text(
            text = "Settings Screen",
            fontSize = 24.sp,
            modifier = Modifier.clickable { navigationController.navigate(SETTINGS) })
    }
}