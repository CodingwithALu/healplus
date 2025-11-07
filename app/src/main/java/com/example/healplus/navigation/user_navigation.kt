package com.example.healplus.navigation
import VerifyEmailScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.model.products.ProductsModel
import com.example.core.model.products.ReviewItem
import com.example.core.model.users.UserModel
import com.example.core.viewmodel.AuthViewModel
import com.example.healplus.feature.authentication.signin.SignInScreen
import com.example.healplus.feature.authentication.signup.SignupScreen
import com.example.healplus.feature.common.widgets.success_screen.SuccessScreen
import com.example.healplus.feature.personalization.profiles.ProfileScreen
import com.example.healplus.feature.personalization.profiles.UpdateProfileScreen
import com.example.healplus.feature.personalization.settings.SettingScreen
import com.example.healplus.feature.shop.cart.AddressScreen
import com.example.healplus.feature.shop.cart.CartScreen
import com.example.healplus.feature.shop.chat.UserChatScreen
import com.example.healplus.feature.shop.collections.CollectionScreen
import com.example.healplus.feature.shop.home.AllReviewsScreen
import com.example.healplus.feature.shop.home.DetailScreen
import com.example.healplus.feature.shop.home.HomeScreen
import com.example.healplus.feature.shop.home.ProductDetailScreen
import com.example.healplus.feature.shop.home.WriteReviewScreen
import com.example.healplus.feature.shop.order.UsersOder
import com.example.healplus.feature.utils.route.Screen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun MyAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable (route = Screen.Login.route) {
            SignInScreen(navController)
        }
        composable (route = Screen.Signup.route){
            SignupScreen(navController)
        }
        composable(route = "${Screen.VerifyEmail.route}/{email}"){ backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")
            VerifyEmailScreen(
                email = email!!,
                navController
            )
        }
        composable(Screen.SuccessScreen.route) {
            SuccessScreen(
                image = "https://mybucket-01laulu2k3.s3.us-east-1.amazonaws.com/json/72462-check-register.json",
                title = "Thành công!",
                subtitle = "Email của bạn đã được xác thực.",
                showEmail = true
            )
        }
        composable("home") {
            HomeScreen(
                navController = navController
            )
        }
        composable(route = "point"){
            UsersOder(navController)
        }
        composable(route = "oderscreen"){
            UsersOder(navController)
        }
        composable(route = "add"){
            UserChatScreen()
        }
        composable("detail/{itemsModel}") {
                backStackEntry ->
            val jsonItem = backStackEntry.arguments?.getString("itemsModel")

            val item = Gson().fromJson(jsonItem, ProductsModel::class.java)
            DetailScreen(
                item = item,
                navController = navController
            )
        }
        composable("productDetail/{product}") {
                backStackEntry ->
            val jsonItem = backStackEntry.arguments?.getString("product")

            val item = Gson().fromJson(jsonItem, ProductsModel::class.java)
            ProductDetailScreen(
                item = item,
                navController = navController
            )
        }
        composable("allReviews/{productName}/{reviewItems}") { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName")?: ""
            val encodedJsonReviews = backStackEntry.arguments?.getString("reviewItems")
            val jsonReviews = URLDecoder.decode(encodedJsonReviews, StandardCharsets.UTF_8.toString())
            val typeToken = object : TypeToken<List<ReviewItem>>() {}.type
            val reviewList: List<ReviewItem> = Gson().fromJson(jsonReviews, typeToken)
            AllReviewsScreen(navController, productName, reviewList)
        }
        composable("writeReview/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            WriteReviewScreen(navController, productId)
        }
        composable("cart") {
            CartScreen(
            navController
            )
        }
//        composable("order_screen/{selectedProducts}/{itemTotal}/{tax}/{quantity}") { backStackEntry ->
//            val selectedProductsJson = backStackEntry.arguments?.getString("selectedProducts") ?: "[]"
//            val totalAmount = backStackEntry.arguments?.getString("itemTotal")?.toDoubleOrNull() ?: 0.0
//            val tax = backStackEntry.arguments?.getString("tax")?.toDoubleOrNull() ?: 0.0
//            val quantity = backStackEntry.arguments?.getString("quantity")?.toInt() ?: 0
//            val selectedProducts: List<ProductsModel> = Gson().fromJson(
//                URLDecoder.decode(selectedProductsJson, "UTF-8"),
//                object : TypeToken<List<ProductsModel>>() {}.type
//            )
//
//            CheckOutScreen(navController, selectedProducts, totalAmount, tax, quantity)
//        }
        composable("address"){
            AddressScreen(navController)
        }

        composable("settings") {
            SettingScreen(navController = navController)
        }
        composable(route = "category/{categoryid}/{categorytitle}",
            arguments = listOf(
                navArgument(name = "categoryid"){
                    type = NavType.StringType
                },
                navArgument(name = "categorytitle"){
                    type = NavType.StringType
                }
            )
        ){navBackStackEntry ->
            navBackStackEntry.arguments?.let {
                    argument ->
                val categoryid = argument.getString("categoryid")
                val categorytitle = argument.getString("categorytitle")
                if (categoryid.isNullOrBlank() || categorytitle.isNullOrBlank()) {
                    Log.e("Navigation123", "Error: Missing categoryid or categorytitle")
                    return@composable
                }
                CollectionScreen(
                    title = categorytitle,
                    id = categoryid,
                    navController = navController
                )
            }
        }
//        composable("search"){
//            SearchScreen(
//                viewModel = viewModel,
//                navController = navController
//            )
//        }
        composable("profile"){
            val viewModel: AuthViewModel = viewModel()
            ProfileScreen(
                viewModel = viewModel,
                navController
            )
        }
        composable("editProfile/{userData}"){
                backStackEntry ->
            val jsonItem = backStackEntry.arguments?.getString("userData")

            val item = Gson().fromJson(jsonItem, UserModel::class.java)
            UpdateProfileScreen(item, navController)
        }
    }
}