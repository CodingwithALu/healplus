
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.healplus.feature.shop.product.widget.ProductInfoItem
import com.example.healplus.feature.utils.route.Screen
import com.google.gson.Gson

@Composable
fun ProductInfoView(
    product: ProductsModel,
    navController: NavController,
    viewModel: ReviewViewModel
) {
    var showProducts by rememberSaveable  { mutableStateOf(false) }
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.inpomation),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = stringResource(R.string.see_all),
                color = Color(0xFF007AFF),
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    navController.navigate("${Screen.InfoProduct.route}/${Uri.encode(Gson().toJson(product))}")
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.trademark) + " ${product.trademark}",
            color = Color(0xFF007AFF),
            fontSize = 14.sp,
            modifier = Modifier
                .clickable { /* Xử lý sự kiện */ }
        )
        ProductInfoItem(stringResource(R.string.categories), product.elements)
        AnimatedVisibility(visible = showProducts) {
            Column {
                ProductInfoItem(stringResource(R.string.dogam_from), product.preparation)
                ProductInfoItem(stringResource(R.string.origa), product.origin)
                ProductInfoItem(stringResource(R.string.Manufacturer), product.manufacturer)
                ProductInfoItem(stringResource(R.string.product), product.productionDate)
                ProductInfoItem(stringResource(R.string.expiry), product.expiry)
                ProductInfoItem(stringResource(R.string.Ingredient), product.ingredient)
                ProductInfoItem(stringResource(R.string.description), product.description)
                SeeAllButton(product, navController, viewModel)
            }
        }
        BouncingIconButton(showProducts = showProducts) {
            showProducts = !showProducts
        }
    }
}