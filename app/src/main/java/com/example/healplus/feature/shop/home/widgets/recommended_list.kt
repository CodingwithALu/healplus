package com.example.healplus.feature.shop.home.widgets

import RecommendedProductItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core.model.products.ProductsModel
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun RecommendedList(items: List<ProductsModel>, navController: NavController) {
    LazyRow (
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(TSizes.DEFAULT_SPACE/2),
        contentPadding = PaddingValues(horizontal = TSizes.DEFAULT_SPACE/2)
    ) {
        items(items.size){ index->
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                RecommendedProductItem(items[index], navController)
            }
        }
    }
}