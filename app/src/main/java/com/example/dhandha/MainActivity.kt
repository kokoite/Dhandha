package com.example.dhandha

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dhandha.header.SimpleHeader
import com.example.dhandha.home.HomeActivity
import com.example.dhandha.profile.ProfileActivity
import com.example.dhandha.servicecontainer.ServiceContainer
import com.example.dhandha.tabbar.BottomTabBar
import com.example.dhandha.tabbar.NavigationItemViewModel
import com.example.dhandha.ui.theme.DhandhaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            DhandhaTheme {
                Surface(modifier = Modifier.fillMaxSize(1f),color = MaterialTheme.colorScheme.background) {
                    DhandhaApp(navController = navController)
                }
            }
        }
    }
}

@Composable
fun DhandhaApp(navController: NavHostController) {
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
            Navigations(navController = navController)
        }
    }
}

@Composable
fun Navigations(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItemViewModel.HomeScreeNavigation.routeId) {
        composable(NavigationItemViewModel.HomeScreeNavigation.routeId) {
            HomeActivity()
        }
        composable(NavigationItemViewModel.ProfileScreeNavigation.routeId) {
            ProfileActivity()
        }
    }
}
