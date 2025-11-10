import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.healplus.R

@Composable
fun ImageThumbnail(
    imageUrl: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backColor = if (isSelected) colorResource(R.color.purple_700) else
        colorResource(R.color.grey)
    Box(modifier = Modifier
        .padding(4.dp)
        .size(40.dp)
        .then(
            if (isSelected) {
                Modifier.border(
                    1.dp,
                    colorResource(R.color.purple_500),
                    RoundedCornerShape(10.dp)
                )
            } else {
                Modifier
            }
        )
        .clip(CircleShape)
        .background(backColor)
        .clickable { onClick() }
        .padding(4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .padding(4.dp)
        )
    }
}
