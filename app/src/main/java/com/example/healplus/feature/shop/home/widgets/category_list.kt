package com.example.healplus.feature.shop.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.core.model.categories.CategoryModel
import com.example.healplus.feature.utils.constants.TSizes
import com.example.healplus.feature.utils.route.Screen

@Composable
fun CategoryList(categories: List<CategoryModel>, navController: NavController) {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    LazyRow(
        modifier = Modifier
            .height(TSizes.APP_BAR_HEIGHT)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(TSizes.DEFAULT_SPACE/2),
        contentPadding = PaddingValues(horizontal = TSizes.DEFAULT_SPACE/2)
    ) {
        items(categories.size) { index ->
            CategoryItem(
                item = categories[index],
                iSelected = selectedIndex == index,
                onItemClick = {
                    navController.navigate(Screen.Collection.route)
                }
            )
        }
    }
}

