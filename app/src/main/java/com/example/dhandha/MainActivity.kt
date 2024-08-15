package com.example.dhandha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dhandha.authentication.AuthenticationActivity
import com.example.dhandha.home.HomeActivity
import com.example.dhandha.mainapp.MainAppActivity
import com.example.dhandha.profile.ProfileActivity
import com.example.dhandha.tabbar.BottomTabBar
import com.example.dhandha.tabbar.NavigationItemViewModel
import com.example.dhandha.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(1f),color = MaterialTheme.colorScheme.background) {
                    val controller = rememberNavController()
                    Routing(navController = controller)

                }
            }
        }
    }
}



@Composable
private fun Routing(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.MainApp.routeId) {

        composable(Screen.Authentication.routeId) {
            AuthenticationActivity(navController)
        }

        composable(Screen.MainApp.routeId) {
            MainAppActivity()
        }
    }
}




sealed class Screen(val routeId: String) {
    object MainApp: Screen(routeId = "mainApp")
    object Authentication: Screen(routeId = "authentication")
}


