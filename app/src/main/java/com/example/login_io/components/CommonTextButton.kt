package com.example.login_io.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonTextButton(
    text1: String,
    text2: String,
    color1: Color,
    color2: Color,
    textDecoration: TextDecoration = TextDecoration.None,
    onClick: () -> Unit,
    horizontalAlignment: Arrangement.Horizontal = Arrangement.Center
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = horizontalAlignment,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text1, color = color1)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text2,
            fontSize = 15.sp,
            modifier = Modifier.clickable { onClick() },
            color = color2,
            textDecoration = textDecoration
        )
    }
}
