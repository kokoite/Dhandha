package com.example.dhandha.services.rent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.remember
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
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun UserListCell(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .padding(0.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick()
            }
        ) {
        Row(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(1f), verticalAlignment = Alignment.CenterVertically) {
           Image(painter = painterResource(id = R.drawable.happy_face), contentDescription = "", modifier = Modifier
               .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
               .height(110.dp)
               .width(100.dp)
               .background(
                   Color.Yellow
               ), contentScale = ContentScale.FillBounds)
            Spacer(modifier = Modifier.width(20.dp))
            UserDetailContainer(Modifier.weight(1f))
        }
    }
}

@Composable
fun UserDetailContainer(modifier: Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NamePhoneContainer(Modifier.fillMaxWidth())
        LastPaymentDateAndAmountContainer(Modifier.fillMaxWidth())
        CurrentPaymentDateAndAmountContainer(Modifier.fillMaxWidth())
    }
}

@Composable
fun NamePhoneContainer(modifier: Modifier) {
    Row(modifier = modifier) {

        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
           Text(text = "Name", style = AppTheme.typography.placeholder)
           Text(text = "Pranjal", style = AppTheme.typography.label)
        }

        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Phone", style = AppTheme.typography.placeholder)
            Text(text = "8219376455", style = AppTheme.typography.label)
        }
    }
}

@Composable
private fun LastPaymentDateAndAmountContainer(modifier: Modifier) {
    Row(modifier) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Pending ", style = AppTheme.typography.placeholder)
            Text(text = "2000", style = AppTheme.typography.label)
        }

        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Rent Amount", style = AppTheme.typography.placeholder)
            Text(text = "20000", style = AppTheme.typography.label)
        }
    }
}

@Composable
fun CurrentPaymentDateAndAmountContainer(modifier: Modifier) {
    Row(modifier) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Current payment Date", style = AppTheme.typography.placeholder)
            Text(text = "20-07-2024", style = AppTheme.typography.label)
        }
    }
}