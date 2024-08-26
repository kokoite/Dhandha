package com.example.dhandha.ui.coaching

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dhandha.R
import com.example.dhandha.data.models.CoachingUserListCell
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun CoachingUserListCell(user: CoachingUserListCell, onClick: ()->Unit) {
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
            AsyncImage(model = Uri.parse(user.image), contentDescription = "", modifier = Modifier
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                .height(110.dp)
                .width(100.dp)
                .background(
                    Color.White
                ), contentScale = ContentScale.FillBounds)
            Spacer(modifier = Modifier.width(20.dp))
            LibraryUserDetailContainer(user, Modifier.weight(1f))
        }
    }
}

@Composable
private fun LibraryUserDetailContainer(user: CoachingUserListCell, modifier: Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NamePhoneContainer(user.name, user.phone,Modifier.fillMaxWidth())
        PendingAndPlanContainer(user.pendingAmount, user.feesAmount, Modifier.fillMaxWidth())
        UpcomingPaymentDateContainer(user.expiryDate, Modifier.fillMaxWidth())
    }
}

@Composable
private fun NamePhoneContainer(name: String, phone: String, modifier: Modifier) {
    Row(modifier = modifier) {

        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Name", style = AppTheme.typography.placeholder)
            Text(text = name, style = AppTheme.typography.label)
        }

        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Phone", style = AppTheme.typography.placeholder)
            Text(text = phone, style = AppTheme.typography.label)
        }
    }
}

@Composable
private fun PendingAndPlanContainer(pending: String, fees: String, modifier: Modifier) {
    Row(modifier) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Pending", style = AppTheme.typography.placeholder)
            Text(text = pending, style = AppTheme.typography.label)
        }

        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Fee", style = AppTheme.typography.placeholder)
            Text(text = fees, style = AppTheme.typography.label)
        }
    }
}

@Composable
private fun UpcomingPaymentDateContainer(date: String, modifier: Modifier) {
    Row(modifier) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(text = "Upcoming payment date", style = AppTheme.typography.placeholder)
            Text(text = date, style = AppTheme.typography.label)
        }
    }
}