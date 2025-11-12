package com.example.healplus.feature.shop.collections.effects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.healplus.feature.common.shimmer.TShimmerEffect
import com.example.healplus.feature.utils.constants.TSizes
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ProductEffects() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TSizes.DEFAULT_SPACE / 4),
        mainAxisSpacing = 4.dp,
        crossAxisSpacing = 4.dp
    ) {
        repeat(6){
            Box(
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp.dp / 2) - 8.dp)
            ) {
                TShimmerEffect(
                    modifier = Modifier.fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
}