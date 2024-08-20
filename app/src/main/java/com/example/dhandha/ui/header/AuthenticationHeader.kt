package com.example.dhandha.ui.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.ui.theme.AppTheme


@Composable
fun AuthenticationHeader() {
    Card(modifier = Modifier.padding(horizontal = 20.dp)
        .fillMaxWidth(1f)
        , colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center ,modifier = Modifier
            .fillMaxWidth(1f)
            .padding(vertical = 12.dp, horizontal = 12.dp)) {
            Image(modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .clip(CircleShape),painter = painterResource(id = R.drawable.happy_face), contentDescription = "")
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Hurry up, Register Now!", style = AppTheme.typography.headerTitle)
        }
    }
}