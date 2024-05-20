
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testcomposeapp.data.Forcast

@Composable
fun HomeScreen() {
    LazyColumn(Modifier.fillMaxSize()) {
        
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