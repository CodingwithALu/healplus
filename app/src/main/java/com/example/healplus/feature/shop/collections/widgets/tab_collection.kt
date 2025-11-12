package com.example.healplus.feature.shop.collections.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.model.categories.CategoryModel
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun CategoryTabCollection(
    tabs: List<CategoryModel>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    appBarHeight: Dp = TSizes.XL,
    colors: TTabBarColors = TTabBarColors.default()
) {
    val dark = isSystemInDarkTheme()
    if (tabs.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.height(appBarHeight),
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = colors.indicatorColor
                )
            },
            divider = {}
        ) {
            tabs.forEachIndexed { index, category ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = category.title,
                            fontSize = 16.sp,
                            color = when {
                                selectedTabIndex == index -> if (dark) colors.selectedLabelDark else colors.selectedLabelLight
                                else -> colors.unselectedLabelColor
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
        }
    }
}

data class TTabBarColors(
    val backgroundDark: Color,
    val backgroundLight: Color,
    val indicatorColor: Color,
    val selectedLabelDark: Color,
    val selectedLabelLight: Color,
    val unselectedLabelColor: Color
) {
    companion object {
        fun default() = TTabBarColors(
            backgroundDark = Color(0xFF000000),
            backgroundLight = Color(0xFFFFFFFF),
            indicatorColor = Color(0xFF0066CC),
            selectedLabelDark = Color(0xFFFFFFFF),
            selectedLabelLight = Color(0xFF0066CC),
            unselectedLabelColor = Color(0xFF555555)
        )
    }
}