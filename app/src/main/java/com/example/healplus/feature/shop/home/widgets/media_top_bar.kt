package com.example.healplus.feature.shop.home.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.core.model.categories.CategoryModel
import com.example.core.model.users.UserModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBar(
    navController: NavController,
    categories: List<CategoryModel>,
    user: UserModel
) {
    var expanded by remember { mutableStateOf(false) }
//    TopAppBar(
//        title = {
//
//        },
//        navigationIcon = {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                IconButton(
//                    onClick = { expanded = true },
//                    modifier = Modifier
//                        .size(40.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Menu, contentDescription = null,
//                        modifier = Modifier
//                            .wrapContentWidth(Alignment.End)
//                    )
//                }
//                val semantics =
//                    Modifier.semantics {
//                        contentDescription = "logo_app"
//                        role = Role.Image
//                    }
//                Layout(
//                    Modifier
//                        .size(120.dp)
//                        .fillMaxWidth()
//                        .wrapContentWidth(Alignment.Start)
//                        .then(semantics)
//                        .clipToBounds()
//                        .paint(
//                            painter = painterResource(id = R.drawable.logo_group_app1),
//                            contentScale = ContentScale.Fit
//                        )
//                ) { _, constraints ->
//                    layout(constraints.minWidth, constraints.minHeight) {}
//                }
//            }
//
//        },
//        actions = {
//            IconButton(onClick = {
//                navController.navigate("search")
//            }) {
//                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
//            }
//            IconButton(onClick = {
//                navController.navigate("add")
//            }) {
//                Icon(imageVector = Icons.Filled.Notifications, contentDescription = null)
//            }
//        }
//    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .zIndex(2f)
            .fillMaxHeight()
            .offset(x = 0.dp, y = (-5).dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color.Yellow)
            ) {
                Column {
                    UserView(user, navController)
                    Notification1("Thông báo", navController)
                }
            }
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.title) },
                    onClick = {
                        navController.navigate("category/${category.idc}/${category.title}")
                        expanded = false
                    }
                )
            }
        }

    }
}

@Composable
fun Notification1(
    tile: String,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(tile)
        IconButton(
            onClick = { navController.navigate("notification") }
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun UserView(user: UserModel, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .clickable {
                navController.navigate("profile")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        user.let { userData ->
            val imageUri = userData.url.let { it?.toUri() }
            Log.d("UserProfileScreen", "localImageUrl: ${userData.url}")
            Log.d("UserProfileScreen", "Parsed imageUri: $imageUri")
            GlideImage(
                imageModel = { imageUri },
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                InfoRow(value = userData.name)
            }
        }
    }
}

@Composable
fun InfoRow(value: String) {
    Text(
        text = value, fontWeight = FontWeight.Light,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}