package com.example.healplus.feature.shop.home

import android.net.Uri
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.viewmodel.AuthSate
import com.example.core.viewmodel.AuthViewModel
import com.example.core.viewmodel.HomeViewmodel
import com.example.healplus.R
import com.example.healplus.feature.common.widgets.texts.TSectionHeading
import com.example.healplus.feature.shop.banner.Banners
import com.example.healplus.feature.shop.home.widgets.CategoryList
import com.example.healplus.feature.shop.home.widgets.DrugStoreInfoScreen
import com.example.healplus.feature.shop.home.widgets.ListItems
import com.example.healplus.ui.theme.errorDarkHighContrast
import com.example.healplus.ui.theme.inverseOnSurfaceLight
import com.example.healplus.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.healplus.ui.theme.inversePrimaryLight
import com.example.healplus.ui.theme.inversePrimaryLightHighContrast
import com.example.healplus.ui.theme.onPrimaryLightMediumContrast
import com.example.healplus.ui.theme.onTertiaryLightHighContrast
import com.example.healplus.ui.theme.primaryDark
import com.example.healplus.ui.theme.surfaceBrightLight
import com.skydoves.landscapist.glide.GlideImage
import kotlin.random.Random

@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
) {
    val viewModel : HomeViewmodel = hiltViewModel()
    val banners by viewModel.banners.observeAsState()
    val categories by viewModel.categories.observeAsState()
    val ingredient by viewModel.ingredient.observeAsState()
    val recommended by viewModel.recommended.observeAsState()
    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showRecommendedLoading by remember { mutableStateOf(true) }
    val authSate = authViewModel.authSate.observeAsState()

    LaunchedEffect(authSate.value) {
        when (authSate.value) {
            is AuthSate.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    Scaffold { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            val (scrollList) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(scrollList) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
            ) {
                item {
                    if (showBannerLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        Banners(banners)
                    }
                }
                item {
                    if (showCategoryLoading) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentAlignment = Alignment.Center
                        ) {
                        }
                    } else {
                        Column {
                            Spacer(modifier = Modifier.height(16.dp))
                            CategoryList(categories!!, navController)
                        }
                    }
                }
                item {
                    if (showRecommendedLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                        }
                    } else {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            TSectionHeading(R.string.recommended)
                            ListItems(recommended!!, navController)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
                item {
                    if (showCategoryLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentAlignment = Alignment.Center
                        ) {
                        }
                    } else {
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IngredientScreen(ingredient, navController)
                        }
                    }
                }
                item {
                    if (showRecommendedLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentAlignment = Alignment.Center
                        ) {
                        }
                    }else {
                        DrugStoreInfoScreen()
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .padding(paddingValues)
                    )
                }
            }
        }

    }


}

@Composable
fun IngredientScreen(
    categoriesItems: MutableList<IngredientsModel>?,
    navController: NavController
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(-1) }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TSectionHeading(
                R.string.prominment_category,
                showSubtitle = false
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val displayedItems = if (isExpanded)
                    categoriesItems
                else
                    categoriesItems?.take(4)

                for (i in displayedItems?.indices!!.step(2)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            IngredientItem(
                                item = displayedItems[i],
                                iSelected = selectedIndex == i,
                                onItemClick = {
                                    selectedIndex = i
                                    navController.navigate(
                                        "category/${displayedItems[i].idIngredient}/${displayedItems[i].title}"
                                    )
                                }
                            )
                        }

                        if (i + 1 < displayedItems.size) {
                            Box(modifier = Modifier.weight(1f)) {
                                IngredientItem(
                                    item = displayedItems[i + 1],
                                    iSelected = selectedIndex == (i + 1),
                                    onItemClick = {
                                        selectedIndex = i + 1
                                        navController.navigate(
                                            "category/${displayedItems[i + 1].idIngredient}/${displayedItems[i + 1].title}"
                                        )
                                    }
                                )
                            }
                        } else {
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
            IconButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = if (isExpanded)
                        Icons.Rounded.KeyboardArrowUp
                    else
                        Icons.Rounded.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Show less" else "Show more",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
@Composable
fun IngredientItem(
    item: IngredientsModel,
    iSelected: Boolean,
    onItemClick: () -> Unit
) {
    val colors = listOf(
        inverseOnSurfaceLight, inversePrimaryLight, surfaceBrightLight, onPrimaryLightMediumContrast,
        inverseOnSurfaceLightMediumContrast, onTertiaryLightHighContrast, inversePrimaryLightHighContrast,
        primaryDark, errorDarkHighContrast
    )
    val backgroundColor1 = colors[Random.nextInt(colors.size)]
    Row(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .background(
                color = backgroundColor1,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .width(150.dp)
            .height(60.dp),
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
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 8.dp),
            textAlign = TextAlign.Start,
            maxLines = 2,
        )
    }
}
@Composable
fun Notification1(tile: String,
                  navController: NavController) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(tile)
        IconButton(
            onClick = {navController.navigate("notification")}
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun UserView(viewModel: AuthViewModel, navController: NavController) {
    val user by viewModel.user.observeAsState()
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp)
        .clickable {
            navController.navigate("profile")
        },
        verticalAlignment = Alignment.CenterVertically) {
        user?.let { userData ->
            val imageUri = userData.url.let { Uri.parse(it) }
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
            Column (
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ){
                InfoRow(value = userData.name)
            }
        }
    }
}
@Composable
fun InfoRow(value: String) {
    Text(text = value, fontWeight = FontWeight.Light,
        modifier = Modifier.padding(bottom = 4.dp))
}