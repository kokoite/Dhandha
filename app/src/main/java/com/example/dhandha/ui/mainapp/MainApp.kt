package com.example.dhandha.ui.mainapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dhandha.ui.home.HomeActivity
import com.example.dhandha.ui.profile.ProfileActivity
import com.example.dhandha.ui.tabbar.BottomTabBar
import com.example.dhandha.ui.tabbar.NavigationItemViewModel


@Composable
fun MainAppActivity(appNavController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomTabBar(navController = navController)

        }) {

        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    it.calculateBottomPadding()
                )
            )
        ) {
            MainAppRouting(navController = navController, parentNavController = appNavController)
        }
    }
}

@Composable
private fun MainAppRouting(navController: NavHostController, parentNavController: NavController) {
    NavHost(navController = navController, startDestination = NavigationItemViewModel.Home.routeId) {
        composable(NavigationItemViewModel.Home.routeId) {
            HomeActivity(parentNavController)
        }
        composable(NavigationItemViewModel.Profile.routeId) {
            ProfileActivity(parentNavController)
        }
    }
}