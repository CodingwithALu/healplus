package com.example.healplus.feature.shop.collections.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCollection(
    title: String,
    navController: NavController
){
    TopAppBar(
        title ={
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = {
            Box {
                IconButton(onClick = {
                    navController.navigate("home")
                }) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = null)
                }
            }
        },
        modifier = Modifier.height(50.dp)
    )
}