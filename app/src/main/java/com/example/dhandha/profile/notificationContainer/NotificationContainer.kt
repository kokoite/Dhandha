package com.example.dhandha.profile.notificationContainer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun NotificationContainer() {
    Card(modifier = Modifier
        .height(300.dp)
        .fillMaxWidth(1f), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Pending Actions", style = AppTheme.typography.containerTitle, modifier = Modifier.padding(horizontal = 12.dp))
            Spacer(modifier = Modifier.height(12.dp))
            NotificationTable()
    }
}

@Composable
fun NotificationTable() {
LazyColumn(modifier = Modifier
    .padding(horizontal = 12.dp)
    .fillMaxSize(1f)
    .border(
        width = 0.1.dp,
        shape = RoundedCornerShape(12.dp),
        color = Color.Gray
    ),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement =  Arrangement.spacedBy(20.dp)) {
    items(listOf("a", "b", "c", "d", "e")) {
        NotificationTableCell()
    }
}
}

@Composable
fun NotificationTableCell() {
    Box(modifier = Modifier
        .fillMaxWidth(1f)
        ) {
        Column(modifier = Modifier.fillMaxWidth(1f)) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(modifier = Modifier
                    .height(15.dp)
                    .width(15.dp),
                    painter = painterResource(id = R.drawable.exclamation_fill),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Black),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Payment Pending", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "You forgot to renew membership for your client xxxx", style = AppTheme.typography.label)
            ActionButton()
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier
                .height(0.2.dp)
                .fillMaxWidth(1f)
                .background(Color.Gray))
        }
    }
}


@Composable
fun ActionButton() {
    Button(
        onClick = {
            print("Button clicked")
        },

        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
        Text(
            text = "Done",
            style = MaterialTheme.typography.titleMedium
        )
    }
}
