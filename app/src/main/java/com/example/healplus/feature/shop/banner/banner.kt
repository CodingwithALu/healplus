
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Column
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
import kotlinx.coroutines.delay

@Composable
fun Banners(banners: List<BannersModel>) {
    AutoSlidingCarousel(banners = banners)
}

@Composable
fun AutoSlidingCarousel(
    banners: List<BannersModel>
) {
    val paperState = rememberPagerState(pageCount = { banners.size })
    val isDragged by paperState.interactionSource.collectIsDraggedAsState()
    LaunchedEffect(isDragged) {
        while (!isDragged) {
            delay(6000)
            val nextPage = (paperState.currentPage + 1) % banners.size
            paperState.animateScrollToPage(nextPage)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(state = paperState) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banners[page].url)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
        DotIndicator(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            totalDots = banners.size,
            selectedIndex = paperState.currentPage,
            dotHeight = 6.dp,
            dotWidthOff = 8.dp,
            dotWidth = 80.dp
        )
    }
}
