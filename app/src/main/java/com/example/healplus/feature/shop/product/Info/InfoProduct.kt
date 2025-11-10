package com.example.healplus.feature.shop.product.Info

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.core.model.products.ProductsModel
import com.example.core.viewmodel.AuthViewModel
import com.example.healplus.R
import com.example.healplus.feature.shop.product.widget.BottomProductBarView
import com.example.healplus.feature.shop.product.widget.ProductInfoItem
import com.example.healplus.feature.shop.product.widget.ProductTopAppBar
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun InfoProductScreen(
    item: ProductsModel,
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
//    val managmentCart = ManagmentCart(LocalContext.current, authViewModel.getUserId().toString())
//    val itemCount = managmentCart.getItemCount()
    Scaffold(
        topBar = {
            ProductTopAppBar(navController)
        },
        bottomBar = {
            BottomProductBarView(onAddCartClick = {
                item.quantity = 1
//                managmentCart.insertFood(item)
            }, navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = TSizes.DEFAULT_SPACE/2),
            contentPadding = paddingValues
        ) {
            item {
                Text(
                    text = "Đặc điểm nổi bật:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                ProductInfoItem(stringResource(R.string.categories), item.elements)
                ProductInfoItem(stringResource(R.string.dogam_from), item.preparation)
                ProductInfoItem(stringResource(R.string.origa), item.origin)
                ProductInfoItem(stringResource(R.string.Manufacturer), item.manufacturer)
                ProductInfoItem(stringResource(R.string.product), item.productionDate)
                ProductInfoItem(stringResource(R.string.expiry), item.expiry)
                ProductInfoItem(stringResource(R.string.Ingredient), item.ingredient)
                ProductInfoItem(stringResource(R.string.description), item.description)
                ProductInfoItem(stringResource(R.string.use), item.toUse)
                ProductInfoItem(stringResource(R.string.toUse), item.uses)
                ProductInfoItem(stringResource(R.string.sideEffects), item.sideEffects)
                ProductInfoItem(stringResource(R.string.preserve), item.preserver)
            }
        }
    }
}