package com.example.login_io.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonButton(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D61E7)),
    shape: Shape = RoundedCornerShape(12.dp),
    onClick: () -> Unit,
    label: String,
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        shape = shape
    ) {
        Text(
            label,
            fontSize = 20.sp
        )
    }
}