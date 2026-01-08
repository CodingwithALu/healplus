package com.example.healplus.navigation

import VerifyEmailScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.core.model.categories.CategoryModel
import com.example.core.model.products.ProductsModel
import com.example.core.model.products.conten.ReviewItem
import com.example.core.model.users.UserModel
import com.example.healplus.feature.authentication.onboarding.LottieLoadingAnimation
import com.example.healplus.feature.authentication.signin.SignInScreen
import com.example.healplus.feature.authentication.signup.SignupScreen
import com.example.healplus.feature.common.widgets.success_screen.SuccessScreen
import com.example.healplus.feature.personalization.profiles.ProfileScreen
import com.example.healplus.feature.personalization.profiles.UpdateProfileScreen
import com.example.healplus.feature.personalization.settings.SettingScreen
import com.example.healplus.feature.shop.cart.AddressScreen
import com.example.healplus.feature.shop.cart.CartScreen
import com.example.healplus.feature.shop.cart.CheckOutScreen
import com.example.healplus.feature.shop.chat.UserChatScreen
import com.example.healplus.feature.shop.collections.CollectionScreen
import com.example.healplus.feature.shop.home.HomeScreen
import com.example.healplus.feature.shop.home.widgets.MediumTopAppBar
import com.example.healplus.feature.shop.order.UsersOder
import com.example.healplus.feature.shop.product.Info.InfoProductScreen
import com.example.healplus.feature.shop.product.ProductScreen
import com.example.healplus.feature.shop.review.AllReviewsScreen
import com.example.healplus.feature.shop.review.WriteReviewScreen
import com.example.healplus.feature.shop.search.SearchScreen
import com.example.healplus.feature.utils.route.Screen
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun MyAppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Slash.route) {
        composable (route = Screen.Slash.route){
            LottieLoadingAnimation(navController)
        }
        composable(route = Screen.Login.route) {
            SignInScreen(navController)
        }
        composable(route = Screen.Signup.route) {
            SignupScreen(navController)
        }
        composable(route = "${Screen.VerifyEmail.route}/{email}") { backStackEntry ->
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
                showEmail = true,
                navController = navController
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(route = "point") {
            UsersOder(navController)
        }
        composable(route = "oderscreen") {
            UsersOder(navController)
        }
        composable(route = "add") {
            UserChatScreen()
        }
        composable("${Screen.Product.route}/{idp}") { backStackEntry ->
            val encodedIdp = backStackEntry.arguments?.getString("idp") ?: ""
            val idp = URLDecoder.decode(encodedIdp, StandardCharsets.UTF_8.toString())
            Log.d("Product", "Received idp: $idp")
            ProductScreen(id = idp, navController = navController)
        }
        composable("${Screen.InfoProduct.route}/{item}") { backStackEntry ->
            val encodedJson = backStackEntry.arguments?.getString("item")
            val jsonItem = encodedJson?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
            val item = jsonItem?.let { Gson().fromJson(it, ProductsModel::class.java) }
            InfoProductScreen(
                item = item,
                navController = navController
            )
        }
        composable("${Screen.Review.route}/{productName}/{reviewItems}") { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            val encodedJsonReviews = backStackEntry.arguments?.getString("reviewItems")
            val jsonReviews =
                URLDecoder.decode(encodedJsonReviews, StandardCharsets.UTF_8.toString())
            val typeToken = object : TypeToken<List<ReviewItem>>() {}.type
            val reviewList: List<ReviewItem> = Gson().fromJson(jsonReviews, typeToken)
            AllReviewsScreen(navController, productName, reviewList)
        }
        composable("${Screen.WriteReview.route}/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            WriteReviewScreen(navController, productId)
        }
        composable(route = "${Screen.MediaAppBar.route}/{category}/{user}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("user") { type = NavType.StringType }
            )) { backStackEntry ->
            val categoryArg = backStackEntry.arguments?.getString("category") ?: ""
            val userArg = backStackEntry.arguments?.getString("user") ?: ""

            val categoryJson = URLDecoder.decode(
                categoryArg,
                StandardCharsets.UTF_8.toString()
            )

            val userJson = URLDecoder.decode(
                userArg,
                StandardCharsets.UTF_8.toString()
            )

            val categoryType = object : TypeToken<List<CategoryModel>>() {}.type
            val categoryList: List<CategoryModel> =
                Gson().fromJson(categoryJson, categoryType)

            val userModel: UserModel =
                Gson().fromJson(userJson, UserModel::class.java)
            MediumTopAppBar(
                navController = navController,
                categories = categoryList,
                user = userModel
            )

        }
        composable(Screen.Order.route) {
            CartScreen(
                navController
            )
        }
        composable("${Screen.CheckoutScreen.route}/{selectedProducts}/{itemTotal}/{tax}/{quantity}") { backStackEntry ->
            val selectedProductsJson = backStackEntry.arguments?.getString("selectedProducts") ?: "[]"
            val totalAmount = backStackEntry.arguments?.getString("itemTotal")?.toDoubleOrNull() ?: 0.0
            val tax = backStackEntry.arguments?.getString("tax")?.toDoubleOrNull() ?: 0.0
            val quantity = backStackEntry.arguments?.getString("quantity")?.toInt() ?: 0
            val selectedProducts: List<ProductsModel> = Gson().fromJson(
                URLDecoder.decode(selectedProductsJson, "UTF-8"),
                object : TypeToken<List<ProductsModel>>() {}.type
            )
            CheckOutScreen(navController, selectedProducts, totalAmount, tax, quantity)
        }
        composable("address") {
            AddressScreen(navController)
        }

        composable(Screen.Setting.route) {
            SettingScreen(navController = navController)
        }
        composable(route = Screen.Collection.route) {
            CollectionScreen(
                navController = navController
            )
        }
        composable(Screen.Search.route){
            SearchScreen(
                navController = navController
            )
        }
        composable("${Screen.Profile.route}/{user}",
            arguments = listOf(
                navArgument("user") { type = NavType.StringType }
            )) { backStackEntry ->
            val userArg = backStackEntry.arguments?.getString("user") ?: ""
            val userJson = URLDecoder.decode(
                userArg,
                StandardCharsets.UTF_8.toString()
            )
            val userModel: UserModel =
                Gson().fromJson(userJson, UserModel::class.java)
            ProfileScreen(
                userModel,
                navController
            )
        }
        composable("editProfile/{userData}") { backStackEntry ->
            val jsonItem = backStackEntry.arguments?.getString("userData")
            val item = Gson().fromJson(jsonItem, UserModel::class.java)
            UpdateProfileScreen(item, navController)
        }
        // update
    }
}