package com.example.healplus.feature.shop.collections

import ElementList
import IngredientListCollection
import ProductListCollections
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.viewmodel.apiviewmodel.CollectionViewModel
import com.example.healplus.feature.shop.collections.effects.CollectionEffects
import com.example.healplus.feature.shop.collections.effects.ProductEffects
import com.example.healplus.feature.shop.collections.widgets.ButtonShow
import com.example.healplus.feature.shop.collections.widgets.CategoryTabCollection
import com.example.healplus.feature.shop.collections.widgets.ShowTitleIngredientCollection
import com.example.healplus.feature.shop.collections.widgets.TopBarCollection
import com.example.healplus.feature.utils.constants.TSizes
import kotlinx.coroutines.launch


@Composable
fun CollectionScreen(
    navController: NavController
) {
    val viewModel: CollectionViewModel = hiltViewModel()
    val category by viewModel.category.observeAsState(emptyList())
    val ingredients by viewModel.ingredient.observeAsState(emptyList())
    val element by viewModel.element.observeAsState(emptyList())
    val product by viewModel.currentCall.observeAsState(emptyList())
    val isLoading = viewModel.isLoading
    var selectedCategoryId by rememberSaveable { mutableStateOf("") }
    var selectedIngredientId by remember { mutableStateOf("") }
    var selectedElementId by remember { mutableStateOf("") }
    var displayState by remember { mutableStateOf(DisplayState.INGREDIENTS_FOR_CATEGORY) }
    val pagerState = rememberPagerState(pageCount = { category.size })
    val coroutineScope = rememberCoroutineScope()
    val PREVIEW_COUNT = 6

    // show all products flag (reset when page changes)
    var showMore by remember { mutableStateOf(false) }

    LaunchedEffect(pagerState.currentPage) {
        val newIndex = pagerState.currentPage
        val categoryId = category.getOrNull(newIndex)?.idc.orEmpty()
        if (categoryId.isNotEmpty() && categoryId != selectedCategoryId) {
            selectedCategoryId = categoryId
        }
        // reset preview when user swipes to another page
        showMore = false
    }
    LaunchedEffect(category) {
        if (category.isNotEmpty()) {
            selectedCategoryId = category.first().idc
        }
    }
    LaunchedEffect(selectedCategoryId) {
        if (selectedCategoryId.isNotEmpty()) {
            viewModel.fetchIngredientAndProductFromCategory(selectedCategoryId)
            selectedIngredientId = ""
            selectedElementId = ""
            displayState = DisplayState.INGREDIENTS_FOR_CATEGORY
        }
    }
    LaunchedEffect(selectedIngredientId) {
        if (selectedIngredientId.isNotEmpty()) {
            viewModel.fetchElementAndProductFromIngredient(selectedIngredientId)
            selectedElementId = ""
            displayState = DisplayState.ELEMENTS_FOR_INGREDIENT
        }
    }
    LaunchedEffect(selectedElementId) {
        if (selectedElementId.isNotEmpty()) {
            viewModel.fetchProductByElement(selectedElementId)
            displayState = DisplayState.PRODUCT_FOR_ELEMENT
        }
    }

    Scaffold(
        topBar = {
            TopBarCollection(
                category.find { it.idc == selectedCategoryId }?.title ?: "", navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CategoryTabCollection(
                tabs = category,
                selectedTabIndex = category.indexOfFirst { it.idc == selectedCategoryId }
                    .coerceAtLeast(0),
                onTabSelected = { index ->
                    selectedCategoryId = category.getOrNull(index)?.idc ?: ""
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                    showMore = false
                }
            )
            Spacer(modifier = Modifier.height(TSizes.SM))
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (displayState == DisplayState.INGREDIENTS_FOR_CATEGORY && ingredients.isNotEmpty()) {
                        item {
                            if (isLoading) {
                                CollectionEffects()
                            } else {
                                IngredientListCollection(
                                    ingredients = if (showMore) ingredients else ingredients.take(PREVIEW_COUNT),
                                    event = { it ->
                                        selectedIngredientId = it
                                        showMore = false
                                    }
                                )
                                if(ingredients.size > PREVIEW_COUNT){
                                    Spacer(modifier = Modifier.height(4.dp))
                                    ButtonShow(
                                        showMore
                                    ) {it ->
                                        showMore = it
                                    }
                                }
                            }
                        }
                    }
                    if (displayState == DisplayState.ELEMENTS_FOR_INGREDIENT && element.isNotEmpty()) {
                        item {
                            Column {
                                val ingredientTitle =
                                    ingredients.firstOrNull { it.iding == selectedIngredientId }?.title
                                        ?: "Nguyên liệu"
                                ShowTitleIngredientCollection(title = ingredientTitle) {
                                    displayState = DisplayState.INGREDIENTS_FOR_CATEGORY
                                    selectedElementId = ""
                                }
                                if (isLoading) {
                                    CollectionEffects()
                                } else {
                                    ElementList(
                                        element = if (showMore) element else element.take(PREVIEW_COUNT),
                                        event = { it ->
                                            selectedElementId = it
                                        })
                                    if(element.size > PREVIEW_COUNT){
                                        Spacer(modifier = Modifier.height(4.dp))
                                        ButtonShow(
                                            showMore
                                        ) {it ->
                                            showMore = it
                                        }
                                    }
                                }
                            }
                        }
                    }
                    item {
                        if (displayState == DisplayState.PRODUCT_FOR_ELEMENT) {
                            val elementTitle =
                                element.firstOrNull { it.ide == selectedElementId }?.title
                                    ?: "Phần tử"
                            ShowTitleIngredientCollection(title = elementTitle) {
                                displayState = DisplayState.ELEMENTS_FOR_INGREDIENT
                                selectedElementId = ""
                            }
                        }
                        if (isLoading) {
                            ProductEffects()
                        } else {
                            if (product.isNotEmpty()) {
                                ProductListCollections(
                                    title = "Danh sách sản phẩm",
                                    product = product,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class DisplayState {
    INGREDIENTS_FOR_CATEGORY,
    ELEMENTS_FOR_INGREDIENT,
    PRODUCT_FOR_ELEMENT,
}