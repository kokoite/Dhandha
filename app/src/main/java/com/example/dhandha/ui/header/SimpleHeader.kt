package com.example.dhandha.ui.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.dhandha.R
import com.example.dhandha.ui.theme.AppTheme


@Composable
fun SimpleHeader(title: String, painter: Painter, action: (() -> Unit)? = null) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AppTheme.colorScheme.primaryColor),
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxSize(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .height(60.dp)
                    .width(60.dp)
                    .clip(CircleShape)
                    .background(Color.White).clickable {
                                                       action?.let {
                                                           action()
                                                       }
                    },
                contentScale = ContentScale.FillBounds,
            )
            Text(text = title,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                style = AppTheme.typography.headerTitle
                )
        }
    }
}