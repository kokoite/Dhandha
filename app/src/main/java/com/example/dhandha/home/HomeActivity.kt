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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dhandha.Screen
import com.example.dhandha.header.SimpleHeader
import com.example.dhandha.servicecontainer.ServiceContainer
import com.example.dhandha.services.Service
import com.example.dhandha.services.coaching.CoachingActivity
import com.example.dhandha.services.gym.GymActivity
import com.example.dhandha.services.library.LibraryActivity
import com.example.dhandha.services.rent.RentActivity
import com.example.dhandha.tabbar.NavigationItemViewModel


@Composable
fun HomeActivity(appNavController: NavController) {
    HomeScreen(appNavController)
}


@Composable
private fun HomeScreen(appNavController: NavController) {
    Column (modifier = Modifier
        .fillMaxSize(1f)
        .background(Color(red = 246, green = 246, blue = 255))
        .padding(20.dp)
        .verticalScroll(state = ScrollState(0))){
        Spacer(modifier = Modifier.height(30.dp))
        SimpleHeader()
        Spacer(modifier = Modifier.height(30.dp))
        ServiceContainer("Services we provide") {service ->
            handleService(service, appNavController)
        }
        Spacer(modifier = Modifier.height(30.dp))
        ServiceContainer("Subscribed services") {service ->

        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

private fun handleService(service: Service, navController: NavController) {
    navController.navigate(route = Screen.Rent.routeId) {

    }
}
