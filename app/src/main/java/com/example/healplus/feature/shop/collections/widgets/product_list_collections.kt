import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core.model.products.ProductsModel
import com.example.healplus.feature.shop.collections.widgets.ProductItemCollections
import com.example.healplus.feature.utils.route.Screen
import com.example.healplus.ui.theme.errorDarkHighContrast
import com.example.healplus.ui.theme.inverseOnSurfaceLight
import com.example.healplus.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.healplus.ui.theme.inversePrimaryLight
import com.example.healplus.ui.theme.inversePrimaryLightHighContrast
import com.example.healplus.ui.theme.onPrimaryLightMediumContrast
import com.example.healplus.ui.theme.onTertiaryLightHighContrast
import com.example.healplus.ui.theme.primaryDark
import com.example.healplus.ui.theme.surfaceBrightLight
import com.google.accompanist.flowlayout.FlowRow
import com.google.gson.Gson
import kotlin.random.Random

@Composable
fun ProductListCollections(
    title: String,
    product: List<ProductsModel>,
    navController: NavController
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(4.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            fontSize = 16.sp
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 4.dp
        ) {
            product.forEach { productItem ->
                val colors = listOf(
                    inverseOnSurfaceLight,
                    inversePrimaryLight,
                    surfaceBrightLight,
                    onPrimaryLightMediumContrast,
                    inverseOnSurfaceLightMediumContrast,
                    onTertiaryLightHighContrast,
                    inversePrimaryLightHighContrast,
                    primaryDark,
                    errorDarkHighContrast
                )
                val backgroundColor = colors[Random.nextInt(colors.size)]
                Box(
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp.dp / 2) - 4.dp)
                        .background(
                            backgroundColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            navController.navigate(
                                "${Screen.Product.route}/${
                                    Uri.encode(
                                        Gson().toJson(productItem)
                                    )
                                }"
                            )
                        },
                ) {
                    ProductItemCollections(productItem)
                }
            }
        }
    }
}