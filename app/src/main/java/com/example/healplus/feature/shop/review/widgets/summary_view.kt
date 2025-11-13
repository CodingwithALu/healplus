import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.healplus.R
import com.example.healplus.feature.shop.product.widget.RatingBar
import java.util.Locale

@Composable
fun ReviewSummary(
    averageRating: Float,
    totalReviews: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = String.format(Locale.getDefault(), "%.1f", averageRating),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            RatingBar(rating = averageRating, starSize = 20.dp)
        }
        Text(
            text = stringResource(R.string.based_on_reviews, totalReviews),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}
