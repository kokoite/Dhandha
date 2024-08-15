package com.example.dhandha.services.rent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun RentActivity(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize(1f)) {
        Column {
            RentHeader()
        }
    }
}
