package com.example.healplus.feature.shop.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.viewmodel.HomeViewmodel
import com.example.healplus.R
import com.example.healplus.feature.common.widgets.BottomAppBarr
import com.example.healplus.feature.common.widgets.texts.TSectionHeading
import com.example.healplus.feature.shop.banner.Banners
import com.example.healplus.feature.shop.home.widgets.AnimationTopBar
import com.example.healplus.feature.shop.home.widgets.CategoryList
import com.example.healplus.feature.shop.home.widgets.IngredientList
import com.example.healplus.feature.shop.home.widgets.RecommendedList
import com.example.healplus.feature.shop.home.widgets.StoreInfoScreenWidgets
import com.example.healplus.feature.shop.home.widgets.TAppbarHome
import com.example.healplus.feature.utils.constants.TSizes
import com.example.healplus.feature.utils.route.Screen

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel: HomeViewmodel = hiltViewModel()
    val banners by viewModel.banners.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val ingredient by viewModel.ingredient.collectAsState()
    val recommended by viewModel.recommended.collectAsState()
    val isLoading = viewModel.isLoading
    Scaffold(
        topBar = {
            AnimationTopBar(
                topBar = {
                    TAppbarHome(
                        onAvatarClick = {
                            navController.navigate(Screen.Setting.route)
                        },
                        showNotification = true,
                        searchClick = {
                            navController.navigate(Screen.Search.route)
                        }
                    )
                },
                modifier = Modifier.padding(
                    horizontal = TSizes.MD,
                )
            )
        },
        bottomBar = {
            BottomAppBarr(
                navController = navController
            )
        }
    ) { paddingValues ->
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else{
                LazyColumn (
                    modifier = Modifier.fillMaxSize()
                        .padding(paddingValues)
                ) {
                    item {
                        Banners(banners)
                        Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE/2))
                        CategoryList(categories, navController)
                        Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE/2))
                        TSectionHeading(R.string.recommended)
                        Spacer(modifier = Modifier.height(TSizes.SM/2))
                        RecommendedList(recommended, navController)
                        Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE/2))
                        IngredientList(ingredient, navController)
                        Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE/2))
                        StoreInfoScreenWidgets(navController)
                    }
                }
            }
    }
}
