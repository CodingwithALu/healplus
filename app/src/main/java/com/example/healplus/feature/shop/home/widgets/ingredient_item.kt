package com.example.healplus.feature.shop.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core.model.ingredients.IngredientsModel
import com.example.healplus.feature.utils.constants.TSizes
import com.example.healplus.ui.theme.errorDarkHighContrast
import com.example.healplus.ui.theme.inverseOnSurfaceLight
import com.example.healplus.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.healplus.ui.theme.inversePrimaryLight
import com.example.healplus.ui.theme.inversePrimaryLightHighContrast
import com.example.healplus.ui.theme.onPrimaryLightMediumContrast
import com.example.healplus.ui.theme.onTertiaryLightHighContrast
import com.example.healplus.ui.theme.primaryDark
import com.example.healplus.ui.theme.surfaceBrightLight
import kotlin.random.Random

@Composable
fun IngredientItem(
    item: IngredientsModel,
    onItemClick: () -> Unit
) {
    val colors = listOf(
        inverseOnSurfaceLight,
        inversePrimaryLight,
        surfaceBrightLight,
        onPrimaryLightMediumContrast,
        inverseOnSurfaceLightMediumContrast,
        onTertiaryLightHighContrast,
        inversePrimaryLightHighContrast,
        primaryDark,
        errorDarkHighContrast
    )
    val backgroundColor1 = colors[Random.nextInt(colors.size)]
    Row(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .background(
                color = backgroundColor1,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 2.dp, horizontal = 4.dp)
            .width(124.dp)
            .height(TSizes.APP_BAR_HEIGHT),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.url,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = item.title,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 8.dp),
            textAlign = TextAlign.Start,
            maxLines = 2,
        )
    }
}