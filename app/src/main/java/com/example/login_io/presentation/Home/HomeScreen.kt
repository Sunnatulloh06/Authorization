package com.example.login_io.presentation.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_io.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Box (
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            "Welcome to Home Screen",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
        Spacer(modifier = Modifier.height(80.dp))

        Button(onClick = {
            navController.navigate(Screen.Login_Screen.route) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
            ) {
            Text("Back to Login Screen")
        }
    }
}