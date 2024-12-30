package com.example.login_io

sealed class Screen(val route: String) {
    object Login_Screen: Screen("login")
    object Signup_Screen: Screen("signup")
    object Home_Screen: Screen("home")
}