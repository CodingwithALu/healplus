package com.example.healplus.feature.shop.product

import ImageThumbnail
import ProductInfoView
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.core.model.products.conten.UnitInfo
import com.example.core.viewmodel.ProductViewModel
import com.example.healplus.R
import com.example.healplus.feature.shop.product.widget.BottomProductBarView
import com.example.healplus.feature.shop.product.widget.PointItem
import com.example.healplus.feature.shop.product.widget.PriceText
import com.example.healplus.feature.shop.product.widget.ProductTopAppBar
import com.example.healplus.feature.shop.product.widget.RatingBar
import com.example.healplus.feature.shop.review.ReviewsSection
import com.example.healplus.feature.utils.constants.TSizes
import com.example.healplus.feature.utils.route.Screen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductScreen(
    id: String?,
    navController: NavController,
) {
    val viewModel: ProductViewModel = hiltViewModel()
    val item by viewModel.product.collectAsState()
    val isLoading = viewModel.isLoading
    var selectedImageUrl by remember { mutableStateOf("") }
    var selectedUnits by remember { mutableStateOf(UnitInfo.empty()) }

    // compute formatted price only when selectedUnits.price changes
    val formattedPrice = remember(selectedUnits.price) {
        NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
            .format(selectedUnits.price ?: 0)
            .toString()
    }

    LaunchedEffect(id) {
        // Decode the id because navigation parameter might be URL-encoded (contains # or spaces)
        val decodedId = id?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) } ?: ""
        Log.d("Product", "Received idp: $decodedId")
        if (decodedId.isNotEmpty()) {
            viewModel.fetchProductFromId(decodedId)
        }
    }

    LaunchedEffect(item) {
        // only set defaults if not already chosen to avoid repeated reassignments and recomposition
        if (selectedImageUrl.isEmpty() && item.urls.isNotEmpty()) {
            selectedImageUrl = item.urls.first()
        }
        if (selectedUnits == UnitInfo.empty() && item.unitNames.isNotEmpty()) {
            selectedUnits = item.unitNames.first()
        }
    }

    // ...existing UI code (no change)...
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
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = TSizes.DEFAULT_SPACE / 2)
                    .padding(paddingValues)
            ) {
                item {
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
                }
                item {
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
                            text = "${item.review} " + stringResource(R.string.review),
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${item.sold} " + stringResource(R.string.comment),
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
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
                    ProductInfoView(item, navController)
                    ReviewsSection(
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
                            navController.navigate("${Screen.WriteReview.route}/${URLEncoder.encode(item.idp ?: "", StandardCharsets.UTF_8.toString())}")
                            Log.d("Review", "check id: ${item.idp}")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding())
                }
            }
        }
    }

}



