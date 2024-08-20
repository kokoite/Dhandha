package com.example.dhandha.ui.customviews.servicecontainer


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.ui.customviews.servicecontainer.serviceCell.ServiceCell
import com.example.dhandha.services.Service
import com.example.dhandha.ui.theme.AppTheme


@Composable
fun ServiceContainer(title: String, onClick: (type: Service) -> Unit) {
    val cellViewModel = listOf(
        ServiceCellViewModel(type = Service.Rent, color = Color(red = 247, green = 206, blue = 69), imageId = R.drawable.house_fill, true),
        ServiceCellViewModel(type = Service.Coaching, color = Color(red = 101, green = 196, blue = 102), imageId = R.drawable.graduationcap_fill, false),
        ServiceCellViewModel(type = Service.Gym, color = Color(red = 238, green = 68, blue = 90), imageId = R.drawable.dumbbell_fill, true),
        ServiceCellViewModel(type = Service.Library, color = Color(red = 52, green = 120, blue = 247), imageId = R.drawable.book_fill, false)
    )
    Card(colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth(1f))

    {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title,
                style = AppTheme.typography.containerTitle)
            LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalArrangement = Arrangement.spacedBy(30.dp), modifier = Modifier.padding(vertical = 12.dp)) {
                items(cellViewModel) {
                    ServiceCell(it, onClick)
                }
            }
        }
    }
}



data class ServiceCellViewModel(val type: Service, val color: Color, val imageId: Int, val shouldShowBottomSheet: Boolean)