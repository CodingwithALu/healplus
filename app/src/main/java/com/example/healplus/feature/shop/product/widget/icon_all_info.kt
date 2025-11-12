import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core.model.products.ProductsModel
import com.example.core.viewmodel.ReviewViewModel
import com.example.healplus.R
import com.example.healplus.feature.utils.route.Screen
import com.google.gson.Gson

@Composable
fun SeeAllButton(
    item: ProductsModel,
    navController: NavController,
    viewModel: ReviewViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Screen.InfoProduct.route}/${Uri.encode(Gson().toJson(item))}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.see_all),
            color = Color(0xFF007AFF),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Arrow",
            tint = Color(0xFF007AFF),
            modifier = Modifier
                .size(16.dp)
        )
    }
}