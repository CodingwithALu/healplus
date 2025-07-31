package com.example.healplus.feature.shop.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.core.model.categories.CategoryModel
import com.example.core.viewmodel.authviewmodel.AuthViewModel
import com.example.healplus.R
import com.example.healplus.feature.shop.home.Notification1
import com.example.healplus.feature.shop.home.UserView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBar(navController: NavController,
                    categories: List<CategoryModel>,
                    showCategoryLoading: Boolean,
                    viewModel: AuthViewModel) {
    var expanded by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()
    }
    TopAppBar(
        title = {

        },
        navigationIcon = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu, contentDescription = null,
                        modifier = Modifier
                            .wrapContentWidth(Alignment.End)
                    )
                }
                val semantics =
                    Modifier.semantics {
                        contentDescription = "logo_app"
                        role = Role.Image
                    }
                Layout(
                    Modifier
                        .size(120.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .then(semantics)
                        .clipToBounds()
                        .paint(
                            painter = painterResource(id = R.drawable.logo_group_app1),
                            contentScale = ContentScale.Fit
                        )
                ) { _, constraints ->
                    layout(constraints.minWidth, constraints.minHeight) {}
                }
            }

        },
        actions = {
            IconButton(onClick = {
                navController.navigate("search")
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
            IconButton(onClick = {
                navController.navigate("add")
            }) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = null)
            }
        }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .zIndex(2f)
            .fillMaxHeight()
            .offset(x = 0.dp, y = (-5).dp)
    ) {
        if (showCategoryLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .background(Color.Yellow)
                ) {
                    Column {
                        UserView(viewModel, navController)
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
}