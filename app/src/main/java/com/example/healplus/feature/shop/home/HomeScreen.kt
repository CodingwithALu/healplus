package com.example.healplus.feature.shop.home

import Banners
import android.net.Uri
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.model.banners.BannersModel
import com.example.core.model.categories.CategoryModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.viewmodel.apiviewmodel.ApiCallViewModel
import com.example.core.viewmodel.authviewmodel.AuthSate
import com.example.core.viewmodel.authviewmodel.AuthViewModel
import com.example.core.viewmodel.homeViewmodel
import com.example.healplus.R
import com.example.healplus.common.widgets.texts.TSectionHeading
import com.example.healplus.feature.shop.home.widgets.CategoryList
import com.example.healplus.feature.shop.home.widgets.DrugStoreInfoScreen
import com.example.healplus.feature.shop.home.widgets.ListItems
import com.example.healplus.ui.theme.*
import com.skydoves.landscapist.glide.GlideImage
import kotlin.random.Random

@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
    viewModel: ApiCallViewModel = viewModel(),
    homeViewmodel: homeViewmodel = viewModel()
) {
    val banners = remember { mutableStateListOf<BannersModel>() }
    val categories = remember { mutableStateListOf<CategoryModel>() }
    val ingredient = remember { mutableStateListOf<IngredientsModel>() }
    val recommended = remember { mutableStateListOf<ProductsModel>() }
    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showRecommendedLoading by remember { mutableStateOf(true) }
    var showIngredient by remember { mutableStateOf(true) }
    val authSate = authViewModel.authSate.observeAsState()

    LaunchedEffect(authSate.value) {
        when (authSate.value) {
            is AuthSate.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }
    LaunchedEffect(Unit) {
        homeViewmodel.loadBanners()
        homeViewmodel.banners.observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }

    }
    LaunchedEffect(Unit) {
        viewModel.loadCategory()
        viewModel.categories.observeForever {
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadIngredientCount()
        viewModel.ingredient.observeForever {
            ingredient.clear()
            ingredient.addAll(it)
            showIngredient = false
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadRecommended()
        viewModel.recommended.observeForever {
            recommended.clear()
            recommended.addAll(it)
            showRecommendedLoading = false
        }
    }
    Scaffold(
        topBar = { MediumTopAppBar(
            navController = navController,
            categories = categories,
            showCategoryLoading = showCategoryLoading,
            viewModel = authViewModel
        ) }
    ) { paddingValues ->
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
                            CategoryList(categories, navController)
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
                            ListItems(recommended, navController)
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
    categoriesItems: List<IngredientsModel>,
    navController: NavController
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }

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
            TSectionHeading(R.string.prominment_category, showSubtitle = false)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val displayedItems = if (isExpanded)
                    categoriesItems
                else
                    categoriesItems.take(4)

                for (i in displayedItems.indices step 2) {
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
                                        "category/${displayedItems[i].iding}/${displayedItems[i].title}"
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
                                            "category/${displayedItems[i + 1].iding}/${displayedItems[i + 1].title}"
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
                InfoRow(value = userData.phone)
            }
        }
    }
}
@Composable
fun InfoRow(value: String) {
    Text(text = value, fontWeight = FontWeight.Light,
        modifier = Modifier.padding(bottom = 4.dp))
}