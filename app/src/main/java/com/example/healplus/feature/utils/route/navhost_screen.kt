package com.example.healplus.feature.utils.route

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Slash: Screen("slash")
    object ForgetPass : Screen("forget")
    object Signup : Screen("signup")
    object VerifyEmail : Screen("verifyEmail/{email}")
    object OnBoarding : Screen("onboarding")
    object Home : Screen("Trang chủ")
    object MediaAppBar: Screen("mediaAppBar/{category}/{user}")
    object SuccessScreen: Screen("success_screen_route")
    object Order : Screen("Giỏ hàng")
    object Collection : Screen("Bộ sưu tập")
    object Setting : Screen("Cài đặt")
    object Product : Screen("product")
    object InfoProduct: Screen("infoProduct/{item}")
    object Review: Screen("review")
    object WriteReview: Screen("writeReview/{item}")
    object MediaTopBar: Screen("mediaTopBar")
    object Profile : Screen("profile")
    object ProductDetails : Screen("product_details")
    object StreakList : Screen("streak_list")
    object Search : Screen("search")
    object CollectionPhotos: Screen("collection_photos")
    object CheckoutScreen: Screen("check_out")
}

val routesToHideBottomBar = listOf(
    Screen.OnBoarding.route,
    "photos_list",
    "product_details",
    "Setting"
)
