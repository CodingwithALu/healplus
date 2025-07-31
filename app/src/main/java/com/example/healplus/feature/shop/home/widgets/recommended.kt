package com.example.healplus.feature.shop.home.widgets

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.model.products.ProductsModel
import com.example.healplus.R
import com.example.healplus.ui.theme.*
import com.google.gson.Gson
import java.text.NumberFormat
import java.util.*
import kotlin.random.Random

@Composable
fun ListItems(items: List<ProductsModel>, navController: NavController) {
    LazyRow (
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items.size){
                row ->
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                RecommendedList(items, row, navController)
            }
        }
    }
}
@Composable
fun RecommendedList(items: List<ProductsModel>, row: Int, navController: NavController) {
    val colors = listOf(inverseOnSurfaceLight, inversePrimaryLight, surfaceBrightLight, onPrimaryLightMediumContrast,
        inverseOnSurfaceLightMediumContrast, onTertiaryLightHighContrast, inversePrimaryLightHighContrast,
        primaryDark, errorDarkHighContrast)
    val backgroundColor1 = colors[Random.nextInt(colors.size)]
    val formattedPrice = NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(items[row].price)
    Card (
        modifier = Modifier
            .width(200.dp)
            .wrapContentHeight()
            .clickable {
                navController.navigate("detail/${Uri.encode(Gson().toJson(items[row]))}")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor1
        )

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(280.dp)
        ) {
            AsyncImage(
                model = items[row].product_images.firstOrNull(),
                contentDescription = null,
                modifier = Modifier
                    .width(175.dp)
                    .background(backgroundColor1, shape = RoundedCornerShape(16.dp))
                    .height(180.dp)
                    .padding(top = 16.dp, end = 16.dp, start = 16.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = items[row].name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )
                    Text(
                        text = items[row].rating.toString(),
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                }
                Text(
                    text = formattedPrice,
                    color = colorResource(R.color.purple_200),
                    maxLines = 1,
                    fontSize = 15.sp,
                )
            }
        }
    }
}