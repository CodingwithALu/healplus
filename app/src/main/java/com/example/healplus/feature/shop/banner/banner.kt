package com.example.healplus.feature.shop.banner
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.model.banners.BannersModel
import com.example.healplus.feature.shop.banner.widgets.DotIndicator
import com.example.healplus.feature.utils.constants.TSizes
import kotlinx.coroutines.delay

@Composable
fun Banners(banners: List<BannersModel>?) {
    if (banners.isNullOrEmpty()) return
    AutoSlidingCarousel(banners = banners)
}

@Composable
fun AutoSlidingCarousel(
    banners: List<BannersModel>
) {
    val pagerState = rememberPagerState(pageCount = { banners.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    LaunchedEffect(isDragged, banners.size) {
        if (banners.isEmpty()) return@LaunchedEffect
        while (!isDragged) {
            delay(6000)
            val total = banners.size
            if (total == 0) break
            val nextPage = (pagerState.currentPage + 1) % total
            pagerState.animateScrollToPage(nextPage)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(state = pagerState) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banners[page].url)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = TSizes.BORDER_RADIUS_SM)
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDots = banners.size,
            selectedIndex = pagerState.currentPage,
            dotHeight = 4.dp,
            dotWidthOff = 4.dp,
            dotWidth = 80.dp
        )
    }
}