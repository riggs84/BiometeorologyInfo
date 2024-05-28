import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testcomposeapp.ui.navigation.NavigationHost
import com.example.testcomposeapp.ui.navigation.Routes


@Composable
fun MyApp() {
    val navigationController = rememberNavController()

    Scaffold(bottomBar = { BottomBar(navigationController) }) {
        Column(
            Modifier.fillMaxSize().padding(it)
        ) {
            NavigationHost(navigationController, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun BottomBar(navigationController: NavHostController) {
    val backStackEntry by navigationController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    NavigationBar {
        NavigationBarItem(
            label = { Text(text = Routes.HOME.path) },
            selected = currentDestination?.route?.equals(Routes.HOME.path) ?: false,
            onClick = { navigationController.navigate(Routes.HOME.path) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "home screen navigation",
                )
            }
        )
        NavigationBarItem(
            label = { Text(text = Routes.SETTINGS.path) },
            selected = currentDestination?.route?.equals(Routes.SETTINGS.path) ?: false,
            onClick = { navigationController.navigate(Routes.SETTINGS.path) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = "home screen navigation",
                )
            }
        )
    }
}