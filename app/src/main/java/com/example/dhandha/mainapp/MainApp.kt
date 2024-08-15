package com.example.dhandha.mainapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dhandha.helper.NoRippleTheme
import com.example.dhandha.home.HomeActivity
import com.example.dhandha.profile.ProfileActivity
import com.example.dhandha.tabbar.BottomTabBar
import com.example.dhandha.tabbar.NavigationItemViewModel


@Composable
fun MainAppActivity() {
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
            MainAppRouting(navController = navController)
        }
    }
}

@Composable
private fun MainAppRouting(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItemViewModel.Home.routeId) {
        composable(NavigationItemViewModel.Home.routeId) {
            HomeActivity()
        }
        composable(NavigationItemViewModel.Profile.routeId) {
            ProfileActivity()
        }
    }
}