package com.example.healplus.feature.shop.home.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core.model.ingredients.IngredientsModel
import com.example.healplus.R
import com.example.healplus.feature.common.widgets.texts.TSectionHeading
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun IngredientList(
    categoriesItems: List<IngredientsModel>,
    navController: NavController
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(TSizes.SM / 2)
        ) {
            TSectionHeading(
                R.string.prominment_category,
                showSubtitle = false
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = TSizes.DEFAULT_SPACE / 2),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val displayedItems = if (isExpanded)
                    categoriesItems.take(12)
                else
                    categoriesItems.take(6)
                for (i in displayedItems.indices.step(3)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            IngredientItem(
                                item = displayedItems[i],
                                onItemClick = {
                                    navController.navigate(
                                        "category/${displayedItems[i].idIngredient}/${displayedItems[i].title}"
                                    )
                                }
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            IngredientItem(
                                item = displayedItems[i + 1],
                                onItemClick = {
                                    navController.navigate(
                                        "category/${displayedItems[i + 1].idIngredient}/${displayedItems[i + 1].title}"
                                    )
                                }
                            )
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            IngredientItem(
                                item = displayedItems[i + 2],
                                onItemClick = {
                                    navController.navigate(
                                        "category/${displayedItems[i + 2].idIngredient}/${displayedItems[i + 2].title}"
                                    )
                                }
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(vertical = TSizes.SM/2),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isExpanded)
                            Icons.Rounded.KeyboardArrowUp
                        else
                            Icons.Rounded.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Show less" else "Show more",
                        modifier = Modifier.clickable(
                            onClick = { isExpanded = !isExpanded }
                        )
                    )
                }
            }
        }
    }
}