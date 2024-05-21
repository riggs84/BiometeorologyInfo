import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testcomposeapp.data.repository.Forecast
import com.example.testcomposeapp.ui.homeScreen.HomeScreenViewModel
import com.example.testcomposeapp.ui.homeScreen.ViewState

@Composable
fun HomeScreen(homeViewModel: HomeScreenViewModel = viewModel()) {
    val result by homeViewModel.state.collectAsState()
    when (result) {
        is ViewState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = "LOADING",
                    Modifier.align(Alignment.Center)
                )
            }
        }

        is ViewState.Success -> {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                item {
                    Text(
                        text = "Прогноз на текущий день:",
                        Modifier.fillMaxWidth(),
                        fontSize = 24.sp
                    )
                }
                items((result as ViewState.Success).data.currentDay) {
                    ForecastComponent(data = it)
                }
                if ((result as ViewState.Success).data.nextDay.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.size(24.dp))
                        Text(
                            text = "Прогноз на завтрашний день:",
                            Modifier.fillMaxWidth(),
                            fontSize = 24.sp
                        )
                    }
                    items((result as ViewState.Success).data.nextDay) {
                        ForecastComponent(data = it)
                    }
                }
            }
        }

        is ViewState.Error -> {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = (result as ViewState.Error).err,
                    Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun RatingBar(rating: Int) {
    val color: Color =
        if (rating <= 2) Color.Green else if (rating <= 4) Color.Yellow else Color.Red
    Row(Modifier.fillMaxWidth()) {
        repeat(rating) {
            Image(
                painter = ColorPainter(color), contentDescription = "rating",
                Modifier
                    .size(20.dp, 40.dp)
                    .padding(2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview() {
    Column {
        RatingBar(rating = 1)
        RatingBar(rating = 4)
        RatingBar(rating = 5)
    }
}

@Composable
fun ForecastComponent(data: Forecast) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = data.title, fontSize = 12.sp)
        Spacer(modifier = Modifier.size(2.dp))
        RatingBar(rating = data.counter)
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = data.description, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ForecastComponentPreview() {
    val data = Forecast(
        "Индекс метеочувствительности",
        3,
        "Велика вероятность влияния погодных условий на самочувствие метеозависимых людей."
    )
    ForecastComponent(data = data)
}