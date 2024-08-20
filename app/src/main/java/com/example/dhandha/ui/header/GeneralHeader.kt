package com.example.dhandha.ui.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.ui.theme.AppTheme


@Composable
fun GeneralHeader(title: String, painter: Painter, action: (() -> Unit)? = null)  {
    Box (modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .fillMaxWidth(1f)
        .height(60.dp)
        .background(Color.White), contentAlignment = Alignment.Center) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(0.dp))
            Icon(painter = painter, contentDescription = "", modifier = Modifier
                .height(40.dp)
                .width(40.dp).clickable {
                    action?.let {
                        action()
                    }
                })
            Text(text = title, style = AppTheme.typography.headerTitle)
        }
    }
}