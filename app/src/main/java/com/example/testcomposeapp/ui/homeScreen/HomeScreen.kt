import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testcomposeapp.data.Forcast
import com.example.testcomposeapp.ui.homeScreen.HomeScreenViewModel
import com.example.testcomposeapp.ui.homeScreen.ViewState

@Composable
fun HomeScreen(homeViewModel: HomeScreenViewModel = viewModel()) {
    val result by homeViewModel.state.collectAsState()
    when (result) {
        is ViewState.Loading -> {}
        is ViewState.Success -> {
            Column(Modifier.fillMaxSize()) {
                ForcastComponent(data = (result as ViewState.Success).data)
            }
        }
        is ViewState.Error -> {}
    }

}

@Composable
fun ForcastComponent(data: Forcast) {
    Row(Modifier.fillMaxWidth()) {
        Text(text = data.title)
        Text(text = data.counter.toString())
        Text(text = data.description)
    }
}