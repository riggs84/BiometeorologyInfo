package com.example.testcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(Modifier.fillMaxSize()) {
                val navigationController = rememberNavController()
                NavHost(navController = navigationController, startDestination = "home", modifier = Modifier.weight(1f)) {
                    composable("home") { HomeScreen() }
                    composable("settings") { SettingsScreen() }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Home Screen", modifier = Modifier.clickable { navigationController.navigate("home") })
                    Text(text = "Settings Screen", modifier = Modifier.clickable {  navigationController.navigate("settings") })
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {

}

@Composable
fun SettingsScreen() {}