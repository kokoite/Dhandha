package com.example.dhandha.ui.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dhandha.R

@Composable
fun ProfileHeader() {
        Card(modifier = Modifier.clip(RoundedCornerShape(20.dp)).padding(top = 40.dp),colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Row(modifier = Modifier.fillMaxWidth(1f).padding(horizontal = 20.dp), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Image(modifier = Modifier
                    .padding(vertical = 12.dp)
                    .height(100.dp)
                    .width(100.dp).clip(CircleShape),
                    painter = painterResource(id = R.drawable.happy_face),
                    contentScale = ContentScale.Fit ,
                    contentDescription = "")
                ProfileHeaderData()
            }
        }
}

@Composable
private fun ProfileHeaderData() {
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        Text(text = "Name: Pranjal")
        Text(text = "Email: pranjal@gmail.com", style = MaterialTheme.typography.titleSmall)
        Text(text = "Phone: 8291375546")
    }
}