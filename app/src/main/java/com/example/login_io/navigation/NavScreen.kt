package com.example.login_io.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login_io.CommonViewModel
import com.example.login_io.presentation.Home.HomeScreen
import com.example.login_io.presentation.Login.LoginScreen
import com.example.login_io.presentation.Signup.SignupScreen

@Composable
fun NavScreen() {
    val navController = rememberNavController()
    val viewModel: CommonViewModel = viewModel()
    NavHost(navController, startDestination = Screen.Login_Screen.route) {
        composable(Screen.Login_Screen.route) { LoginScreen(navController, viewModel) }
        composable(Screen.Signup_Screen.route) { SignupScreen(navController, viewModel) }
        composable(Screen.Home_Screen.route) { HomeScreen(navController) }
    }
}