package com.example.dhandha.servicecontainer


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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.servicecontainer.serviceCell.ServiceCell

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ServiceContainer() {
    val cellViewModel = listOf<ServiceCellViewModel>(
        ServiceCellViewModel(color = Color.Yellow, imageId = R.drawable.house_fill),
        ServiceCellViewModel(color = Color.Green, imageId = R.drawable.graduationcap_fill),
        ServiceCellViewModel(color = Color.Red, imageId = R.drawable.dumbbell_fill),
        ServiceCellViewModel(color = Color.Blue, imageId = R.drawable.book_fill))
    Card(colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth(1f))

    {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Services we provide",
                style = MaterialTheme.typography.titleSmall)
            LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalArrangement = Arrangement.spacedBy(30.dp), modifier = Modifier.padding(vertical = 12.dp)) {
                items(cellViewModel) {
                    ServiceCell(it)
                }
            }
        }
    }
}

data class ServiceCellViewModel(val color: Color, val imageId: Int)