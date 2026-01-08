package com.example.healplus.feature.shop.search
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.viewmodel.OrderViewModel
import com.example.healplus.feature.shop.collections.widgets.ProductItemCollections
import com.example.healplus.feature.utils.route.Screen
import com.example.healplus.ui.theme.errorDarkHighContrast
import com.example.healplus.ui.theme.inverseOnSurfaceLight
import com.example.healplus.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.healplus.ui.theme.inversePrimaryLight
import com.example.healplus.ui.theme.inversePrimaryLightHighContrast
import com.example.healplus.ui.theme.onPrimaryLightMediumContrast
import com.example.healplus.ui.theme.onTertiaryLightHighContrast
import com.example.healplus.ui.theme.primaryDark
import com.example.healplus.ui.theme.surfaceBrightLight
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.random.Random

@Composable
fun SearchScreen(
    navController: NavController,
) {
    val viewModel: OrderViewModel = hiltViewModel()
    val product by viewModel.search.observeAsState()
    var isLoading by remember { mutableStateOf(false) }
    var search by remember { mutableStateOf("") }
    var showHistory by remember { mutableStateOf(true) }
    var searchHistory by rememberSaveable { mutableStateOf(listOf<String>()) }

    LaunchedEffect(search) {
        if (search.isNotEmpty()) {
            delay(500)
            showHistory = false
            viewModel.searchProduct(search)
        } else {
            showHistory = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBarSearch(
                navController = navController,
                search = search,
                onSearchChange = { search = it },
                onClearClick = { search = "" }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Hiển thị text "Kết quả tìm kiếm cho: [search]"
            if (search.isNotEmpty()) {
                Text(
                    text = "Kết quả tìm kiếm cho: \"$search\"",
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            } else if (showHistory && search.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Lịch sử tìm kiếm",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    searchHistory.forEach { historyItem ->
                        HistoryItem(
                            text = historyItem,
                            onClick = { search = historyItem },
                            onDelete = {
                                searchHistory = searchHistory.filter { it != historyItem }
                            }
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    item {
                        FlowRow(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth(),
                            mainAxisSpacing = 16.dp,
                            crossAxisSpacing = 16.dp,
                        ) {
                            product?.forEach { productItem ->
                                val colors = listOf(
                                    inverseOnSurfaceLight,
                                    inversePrimaryLight,
                                    surfaceBrightLight,
                                    onPrimaryLightMediumContrast,
                                    inverseOnSurfaceLightMediumContrast,
                                    onTertiaryLightHighContrast,
                                    inversePrimaryLightHighContrast,
                                    primaryDark,
                                    errorDarkHighContrast
                                )
                                val backgroundColor = colors[Random.nextInt(colors.size)]
                                Box(
                                    modifier = Modifier
                                        .width((LocalConfiguration.current.screenWidthDp.dp / 2) - 4.dp)
                                        .background(
                                            backgroundColor,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .clip(RoundedCornerShape(12.dp))
                                        .clickable {
                                            val encodedId = URLEncoder.encode(productItem.idp, StandardCharsets.UTF_8.toString())
                                            navController.navigate("${Screen.Product.route}/$encodedId")
                                        },
                                ) {
                                    ProductItemCollections(productItem)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarSearch(
    navController: NavController,
    search: String,
    onSearchChange: (String) -> Unit,
    onClearClick: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
            ) {
                TextField(
                    value = search,
                    onValueChange = onSearchChange,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    placeholder = { Text("Tìm kiếm sản phẩm...") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                        )
                    },
                    trailingIcon = {
                        if (search.isNotEmpty()) {
                            IconButton(onClick = onClearClick) {
                                Icon(
                                    Icons.Default.Clear,
                                    contentDescription = "Clear search",
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun HistoryItem(
    text: String,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "History",
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
