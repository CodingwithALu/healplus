package com.example.healplus.feature.shop.product

import ImageThumbnail
import ProductInfoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.core.model.products.ProductsModel
import com.example.core.model.products.conten.ReviewItem
import com.example.core.viewmodel.ReviewViewModel
import com.example.healplus.R
import com.example.healplus.feature.shop.product.widget.BottomProductBarView
import com.example.healplus.feature.shop.product.widget.PointItem
import com.example.healplus.feature.shop.product.widget.PriceText
import com.example.healplus.feature.shop.product.widget.ProductTopAppBar
import com.example.healplus.feature.shop.product.widget.RatingBar
import com.example.healplus.feature.shop.review.NewReviewsSection
import com.example.healplus.feature.utils.constants.TSizes
import com.example.healplus.feature.utils.route.Screen
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductScreen(
    item: ProductsModel,
    navController: NavController,
) {
    val reviewViewModel: ReviewViewModel = hiltViewModel()
    var selectedImageUrl by remember { mutableStateOf(item.urls.first()) }
    var selectedUnits by remember { mutableStateOf(item.unitNames.first()) }
    var formattedPrice =
        NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(selectedUnits.price)
            .toString()
    LaunchedEffect(selectedUnits) {
        formattedPrice =
            NumberFormat.getCurrencyInstance(Locale("vi", "VN")).format(selectedUnits.price)
                .toString()
    }
    Scaffold(
        topBar = {
            ProductTopAppBar(navController)
        },
        bottomBar = {
            BottomProductBarView(onAddCartClick = {
                item.quantity = 1
//                managmentCart.insertFood(item)
            }, navController)
        })
    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = TSizes.DEFAULT_SPACE / 2)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = selectedImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            LazyRow {
                items(item.urls) { imageUrl ->
                    ImageThumbnail(
                        imageUrl = imageUrl,
                        isSelected = selectedImageUrl == imageUrl,
                        onClick = { selectedImageUrl = imageUrl }
                    )
                }
            }
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = String.format(Locale.getDefault(), "%.1f", item.rating),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = "${item.review} " + stringResource(R.string.review), color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "${item.sold} " + stringResource(R.string.comment),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(TSizes.DEFAULT_SPACE)

            ) {
                items(item.unitNames) { it ->
                    PriceText(
                        model = it.name,
                        selected = selectedUnits == it,
                        onclick = {
                            selectedUnits = it
                        })
                }
            }
            Text(
                text = formattedPrice,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            PointItem(
                selectedUnits.price!!
            )
            ProductInfoView(item, navController, reviewViewModel)
            NewReviewsSection(
                averageRating = item.rating.toFloat(),
                totalReviews = item.review,
                individualReviews = item.reviewItems,
                onSeeAllReviewsClick = {
                    navController.navigate(
                        "${Screen.Review.route}/${item.name}/${
                            URLEncoder.encode(
                                Gson().toJson(item.reviewItems),
                                StandardCharsets.UTF_8.toString()
                            )
                        }"
                    )
                },
                onWriteReviewClick = {
                    navController.navigate("${Screen.WriteReview.route}/${item.idp}")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding())
        }

    }

}

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

@Composable
fun ReviewCard(review: ReviewItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            review.ulr?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = "${review.name} profile picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Column {
                Text(
                    text = review.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                RatingBar(rating = review.rating, starSize = 16.dp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = review.date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = review.comment,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}

