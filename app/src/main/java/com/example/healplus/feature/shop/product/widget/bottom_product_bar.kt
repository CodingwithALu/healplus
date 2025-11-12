package com.example.healplus.feature.shop.product.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healplus.R

@Composable
fun BottomProductBarView(
    onAddCartClick: () -> Unit,
    navController: NavController
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
            .height(56.dp),
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("add") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.comment_24px),
                    contentDescription = null,
                    tint = Color(0xFF0066CC),
                    modifier = Modifier.size(16.dp)
                )
            }
            Button(
                onClick = {
                    onAddCartClick()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0055FF))
            ) {
                Text(stringResource(R.string.addcart),
                    fontSize = 14.sp, color = Color.White)
            }
        }
    }
}