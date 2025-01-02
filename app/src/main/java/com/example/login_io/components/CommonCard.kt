package com.example.login_io.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonCard(
    text: String,
    iconResId: Int
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(vertical = 4.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
            .clickable {  },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
          Row(
              verticalAlignment = Alignment.CenterVertically
          ) {
              Image(
                  painter = painterResource(iconResId),
                  contentDescription = text,
                  modifier = Modifier
                      .size(24.dp)
                      .align(Alignment.CenterVertically)
              )
              Text(
                  text = text,
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.align(Alignment.CenterVertically)
              )
          }
        }
    }
}