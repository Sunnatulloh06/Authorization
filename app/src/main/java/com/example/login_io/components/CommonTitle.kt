package com.example.login_io.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CommonTitle(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}
