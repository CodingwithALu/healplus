package com.example.healplus.feature.common.widgets.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.healplus.feature.common.shimmer.TShimmerEffect
import com.example.healplus.feature.utils.helpers.THelperFunctions

@Composable
fun TCircularImage(
    modifier: Modifier = Modifier,
    image: String = "",
    fit: ContentScale = ContentScale.Crop,
    isNetworkImage: Boolean = false,
    drawableResId: Int? = null,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val isDark = THelperFunctions.isDarkMode()
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .border(width = 2.dp,
                color = if (isDark) Color.White else Color.Black,
                CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (isNetworkImage) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(image)
                    .crossfade(true)
                    .build()
            )
            when (painter.state) {
                is AsyncImagePainter.State.Loading -> {
                    TShimmerEffect()
                }

                is AsyncImagePainter.State.Error -> {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Image error"
                    )
                }

                else -> {

                }
            }
            Image(
                painter = painter,
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize(),
                contentScale = fit
            )
        } else {
            val localPainter: Painter = if (drawableResId != null) {
                painterResource(id = drawableResId)
            } else {
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data("file:///android_asset/$image")
                        .build()
                )
            }
            Image(
                painter = localPainter,
                contentDescription = null,
                modifier = modifier
                    .fillMaxSize(),
                contentScale = fit
            )
        }
    }
}