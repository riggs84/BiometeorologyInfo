import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testcomposeapp.data.Forecast
import com.example.testcomposeapp.ui.homeScreen.HomeScreenViewModel
import com.example.testcomposeapp.ui.homeScreen.ViewState

@Composable
fun HomeScreen(homeViewModel: HomeScreenViewModel = viewModel()) {
    val result by homeViewModel.state.collectAsState()
    when (result) {
        is ViewState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                Text(text = "LOADING", Modifier.align(Alignment.Center))
            }
        }
        is ViewState.Success -> {
            Column(Modifier.fillMaxSize()) {
                ForecastComponent(data = (result as ViewState.Success).data)
            }
        }
        is ViewState.Error -> {
            Box(Modifier.fillMaxSize()) {
                Text(text = (result as ViewState.Error).err, Modifier.align(Alignment.Center))
            }
        }
    }

}

@Composable
fun ForecastComponent(data: Forecast) {
    Row(Modifier.fillMaxWidth()) {
        Text(text = data.title)
        Text(text = data.counter.toString())
        Text(text = data.description)
    }
}