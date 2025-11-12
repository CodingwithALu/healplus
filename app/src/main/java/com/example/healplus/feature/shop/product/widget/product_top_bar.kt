package com.example.healplus.feature.shop.product.widget

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healplus.feature.utils.route.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopAppBar(navController: NavController, itemCount: Int = 0) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Thông tin sản phẩm",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(Screen.Order.route) }) {
                BadgedBox(
                    badge = {
                        if (itemCount > 0) {
                            Badge {
                                Text(itemCount.toString())
                            }
                        }
                    }
                ){
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)
                }
            }
        },
        modifier = Modifier.height(50.dp)
    )
}