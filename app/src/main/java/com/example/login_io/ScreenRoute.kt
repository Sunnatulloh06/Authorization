package com.example.login_io

sealed class Screen(val route: String) {
    object Login_Screen: Screen("Login_Screen")
    object Signup_Screen: Screen("Signup_Screen")
    object Home_Screen: Screen("Home_Screen")
}