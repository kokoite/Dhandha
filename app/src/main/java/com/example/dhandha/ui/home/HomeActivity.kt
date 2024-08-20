package com.example.dhandha.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dhandha.R
import com.example.dhandha.Screen
import com.example.dhandha.ui.botttomsheet.ServiceBottomSheet
import com.example.dhandha.ui.header.SimpleHeader
import com.example.dhandha.ui.customviews.servicecontainer.ServiceContainer
import com.example.dhandha.services.Service


@Composable
fun HomeActivity(appNavController: NavController) {
    HomeScreen(appNavController)
}


@Composable
private fun HomeScreen(appNavController: NavController) {
    val shouldShowBottomSheet = remember {
        mutableStateOf(false
        )
    }
    Column (modifier = Modifier
        .fillMaxSize(1f)
        .background(Color(red = 246, green = 246, blue = 255))
        .padding(20.dp)
        .verticalScroll(state = ScrollState(0))){
        Spacer(modifier = Modifier.height(30.dp))
        SimpleHeader(title = "Welcome back Pranjal!!", painter = painterResource(id = R.drawable.happy_face))
        Spacer(modifier = Modifier.height(30.dp))
        ServiceContainer("Services we provide") {service ->
            shouldShowBottomSheet.value = true
        }
        Spacer(modifier = Modifier.height(30.dp))
        ServiceContainer("Subscribed services") {service ->
            handleService(service, appNavController)
        }
        Spacer(modifier = Modifier.height(20.dp))
        if(shouldShowBottomSheet.value) {
            ServiceBottomSheet {
                shouldShowBottomSheet.value = false
            }
        }
    }
}


private fun handleService(service: Service, navController: NavController) {

    when(service) {
         is Service.Gym ->
             navController.navigate(Screen.Gym.routeId)
        is Service.Coaching ->
            navController.navigate(Screen.Coaching.routeId)
        is Service.Rent ->
            navController.navigate(Screen.Rent.routeId)
        is Service.Library ->
            navController.navigate(Screen.Library.routeId)
    }
}
