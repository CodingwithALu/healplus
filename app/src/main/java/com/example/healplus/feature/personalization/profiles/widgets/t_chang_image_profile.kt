
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healplus.feature.common.styles.TSpacerStyle
import com.example.healplus.feature.common.widgets.TAvatarImage

@Composable
fun ChangImageProfile(
    urlimg: String,
    title: String,
    imagePickerLauncher: ActivityResultLauncher<String>
) {
    Box(contentAlignment = Alignment.Center) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            TAvatarImage(
                uploadedImageUrls = urlimg
            )
            TSpacerStyle(4.dp)
            Text(
                text = title,
                color = Color.Blue,
                fontSize = 14.sp,
                modifier = Modifier.clickable { imagePickerLauncher.launch("image/*") }
            )
        }
    }
}