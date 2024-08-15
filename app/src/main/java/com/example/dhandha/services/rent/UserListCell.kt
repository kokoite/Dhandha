package com.example.dhandha.services.rent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dhandha.R

@Preview(showBackground = true, heightDp = 400, widthDp = 394)
@Composable
fun UserListCell() {
    Box(modifier = Modifier.padding(20.dp).background(Color.Red).clip(
        RoundedCornerShape(20.dp)
    ).border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)).clipToBounds()) {
        Row(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
           Image(painter = painterResource(id = R.drawable.happy_face), contentDescription = "", modifier = Modifier
               .height(200.dp)
               .width(150.dp)
               .background(
                   Color.Yellow
               ))
            Spacer(modifier = Modifier.width(20.dp))
            UserDetailContainer(Modifier.weight(1f))
        }
    }
}

@Composable
fun UserDetailContainer(modifier: Modifier) {

}