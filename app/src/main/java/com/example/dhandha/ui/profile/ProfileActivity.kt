package com.example.dhandha.ui.profile

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
import androidx.navigation.NavController
import com.example.dhandha.ui.header.ProfileHeader
import com.example.dhandha.ui.profile.notificationContainer.NotificationContainer
import com.example.dhandha.ui.customviews.servicecontainer.ServiceContainer
import com.example.dhandha.services.Service

@Composable
fun ProfileActivity(appNavController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize(1f)
        .background(Color(red = 246, green = 246, blue = 255))
        .padding(20.dp)
        .verticalScroll(ScrollState(0))) {
        ProfileHeader()
        Spacer(modifier = Modifier.height(20.dp))
        ServiceContainer("Subscribed services", ::handleService)
        Spacer(modifier = Modifier.height(20.dp))
        NotificationContainer()
        Spacer(modifier = Modifier.height(30.dp))
    }
}

private fun handleService(service: Service) {

}