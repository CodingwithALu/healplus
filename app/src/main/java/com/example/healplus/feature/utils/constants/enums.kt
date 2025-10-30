package com.example.healplus.utils.constants

enum class ConnectionType {
    WIFI,
    MOBILE,
    ETHERNET,
    NONE
}
enum class ScreenDestination {
    ONBOARDING,
    LOGIN,
    VERIFY_EMAIL,
    MAIN,
    PROFILE_SETUP
}
enum class ErrorType {
    NETWORK,
    AUTHENTICATION,
    VALIDATION,
    PERMISSION,
    GENERAL
}