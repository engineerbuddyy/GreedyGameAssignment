import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greedygamequoteappassignment.Presentation.BannerSlider
import com.example.greedygamequoteappassignment.Presentation.Nav.QuoteScreen
import com.example.greedygamequoteappassignment.data.BannerData
import com.example.greedygamequoteappassignment.data.QuoteModel
import com.example.greedygamequoteappassignment.ui.theme.Bold35
import com.example.greedygamequoteappassignment.ui.theme.Normal12
import com.example.greedygamequoteappassignment.viewModel.QuoteViewModel


// Gradient color palette
val gradientList = listOf(
    Color(0xFFEF5350), // red
    Color(0xFFAB47BC), // purple
    Color(0xFF42A5F5), // blue
    Color(0xFF26A69A), // teal
    Color(0xFF66BB6A), // green
    Color(0xFFFFA726)  // orange
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: QuoteViewModel,
    navController: NavController
) {
    val quotes = viewModel.quotes.collectAsState().value

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            Text(
                "Explore",
                style = MaterialTheme.typography.Bold35,
            )
        }

        item {
            Text(
                "Awesome Quotes from our Community",
                style = MaterialTheme.typography.Normal12.copy(color = Color.Black.copy(alpha = 0.8f))
            )
        }


        // Banner Slider
        item {
            BannerSlider(
                bannerList = BannerData.getBanners()
            )

        }

        // Spacer
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Quotes List
        items(quotes.size) { index ->
            val quote =
                quotes[index]                           // /// CHANGED - define current quote
            val topColor = gradientList.random()
            val bottomColor = gradientList.random().copy(alpha = 0.7f)

            QuotesCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .combinedClickable(                      // /// CHANGED - use combinedClickable here
                        onClick = { /* optional */ },
                        onLongClick = {
                            navController.navigate(QuoteScreen.Detail.createRoute(quote.id)) // /// CHANGED
                        }
                    ),
                quoteModel = quote,
                backgroundColors = listOf(topColor, bottomColor),
                onLongPress = {
                    viewModel.toggleFavorite(quote.id)
                }
            )
        }
    }
}

@Composable
fun QuotesCard(
    modifier: Modifier = Modifier,
    quoteModel: QuoteModel,
    backgroundColors: List<Color>,
    onLongPress: () -> Unit,

    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .clip(RoundedCornerShape(16.dp))
    )

    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.linearGradient(backgroundColors))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircleShapeComponent(alpha = 0.1f, color = Color.White)
                SpacerWeight1f()
                // Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
               // Icon(Icons.Default.FavoriteBorder, contentDescription = "Fav", tint = Color.White)
            }

            SpacerWeight1f()

            Text(
                text = quoteModel.text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    lineHeight = 16.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "- ${quoteModel.author}",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 16.sp
                ),
                modifier = modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun RowScope.SpacerWeight1f() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun ColumnScope.SpacerWeight1f() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun CircleShapeComponent(alpha: Float, color: Color) {
    Surface(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape),
        color = color.copy(alpha = alpha)
    ) {}
}
