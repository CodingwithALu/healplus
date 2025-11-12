package com.example.healplus.feature.utils.route

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object ForgetPass : Screen("forget")
    object Signup : Screen("signup")
    object VerifyEmail : Screen("verifyEmail/{email}")
    object OnBoarding : Screen("onboarding")
    object Home : Screen("Home")
    object MediaAppBar: Screen("mediaAppBar/{category}/{user}")
    object SuccessScreen: Screen("success_screen_route")
    object Order : Screen("Order")
    object Collection : Screen("Collection")
    object Favorite : Screen("Favorite")
    object Product : Screen("product/{item}")
    object InfoProduct: Screen("infoProduct/{item}")
    object Review: Screen("review")
    object WriteReview: Screen("writeReview/{item}")
    object CreateReview: Screen("createReview")
    object Setting : Screen("Setting")
    object PhotosList : Screen("photos_list")
    object ProductDetails : Screen("product_details")
    object StreakList : Screen("streak_list")
    object Search : Screen("search")
    object CollectionPhotos: Screen("collection_photos")
}

val routesToHideBottomBar = listOf(
    Screen.OnBoarding.route,
    "photos_list",
    "product_details",
    "Setting"
)
