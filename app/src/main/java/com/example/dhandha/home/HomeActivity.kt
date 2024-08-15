package com.example.dhandha.home

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dhandha.header.SimpleHeader
import com.example.dhandha.servicecontainer.ServiceContainer


@Composable
fun HomeActivity() {
    Column (modifier = Modifier
        .fillMaxSize(1f)
        .background(Color(red = 246, green = 246, blue = 255))
        .padding(20.dp)
        .verticalScroll(state = ScrollState(0))){
        Spacer(modifier = Modifier.height(30.dp))
        SimpleHeader()
        Spacer(modifier = Modifier.height(30.dp))
        ServiceContainer("Services we provide", ::handleService)
        Spacer(modifier = Modifier.height(30.dp))
        ServiceContainer("Subscribed services", ::handleService)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

private fun handleService(service: Service) {
    Log.d("TAG", "handleService: $service")
}


sealed class Service {
    object Rent: Service()
    object Coaching: Service()
    object Gym: Service()
    object Library: Service()
}