package com.example.healplus.acitivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.healplus.navigation.MyAppNavigation
import com.example.healplus.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                    ClientApp()
            }
        }
    }
}
@Composable
fun ClientApp(){
    val navController = rememberNavController()
//    val navItemList = listOf(
//        NavItemModel.DrawableResItem(route = "home", label = stringResource(id = R.string.home), R.drawable.home_24px, badgeCount = 0),
//        NavItemModel.DrawableResItem(route = "point", label = stringResource(id = R.string.poit), R.drawable.orders_24px, badgeCount = 0),
//        NavItemModel.DrawableResItem(route = "add", label = "Liên hệ", R.drawable.contacts_24dp, badgeCount = 0),
//        NavItemModel.DrawableResItem(route = "cart", label = stringResource(R.string.cart), R.drawable.shopping_cart_24px, badgeCount = managementCart.getItemCount()),
//        NavItemModel.DrawableResItem(route = "settings", label = stringResource(R.string.settings), R.drawable.settings_24px, badgeCount = 0),
//    )
//    var selectedIndex by remember {
//        mutableIntStateOf(0)
//    }
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//    val routesToHideBottomBar = listOf(
//        "detail/{itemsModel}",
//        "cart",
//        "add",
//        "profile",
//        "order_screen/{selectedProducts}/{itemTotal}/{tax}/{quantity}",
//        "address",
//        "editProfile/{userData}",
//        "writeReview/{productId}",
//        "allReviews/{productName}/{reviewItems}",
//        "productDetail/{product}",
//    )
//    LaunchedEffect(navController.currentBackStackEntry) {
//        val currentRoute1 = navController.currentBackStackEntry?.destination?.route
//        selectedIndex = navItemList.indexOfFirst { it.route == currentRoute1 }
//    }
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            if (currentRoute !in routesToHideBottomBar) {
//                TNavigationBar(
//                    navItemList = navItemList,
//                    selectedIndex = selectedIndex,
//                    onItemSelected = { index ->
//                        selectedIndex = index
//                        navController.navigate(navItemList[index].route) {
//                            popUpTo("cart") { inclusive = false }
//                            launchSingleTop = true
//                        }
//                    }
//                )
//            }
//        }
//    ) { innerPadding ->
        MyAppNavigation(
            navController = navController
        )
//    }
}


