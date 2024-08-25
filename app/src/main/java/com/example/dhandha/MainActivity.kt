package com.example.dhandha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dhandha.ui.authentication.AuthenticationActivity
import com.example.dhandha.ui.mainapp.MainAppActivity
import com.example.dhandha.ui.coaching.CoachingActivity
import com.example.dhandha.ui.coaching.detail.CoachingUserDetailAcitivty
import com.example.dhandha.ui.gym.GymActivity
import com.example.dhandha.ui.rent.ui.RentScreen
import com.example.dhandha.ui.rent.ui.create.CreateTenantScreen
import com.example.dhandha.ui.dashboard.RentDashboardActivity
import com.example.dhandha.ui.gym.detail.GymUserDetailActivity
import com.example.dhandha.ui.library.LibraryActivity
import com.example.dhandha.ui.library.detail.LibraryUserDetailActivity
import com.example.dhandha.services.rent.detail.RentDetailActivity
import com.example.dhandha.ui.rent.viewmodel.RentViewModel
import com.example.dhandha.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

val NavControllerCompositionLocal = compositionLocalOf<NavController> {
    error("Controller not provided")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(1f),color = MaterialTheme.colorScheme.background) {
                    val controller = rememberNavController()
                    CompositionLocalProvider(NavControllerCompositionLocal provides controller) {
                        Routing(navController = controller)
                    }
                }
            }
        }
    }
}

@Composable
private fun Routing(navController: NavHostController) {

    val rentViewModel: RentViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Rent.routeId) {

        composable(Screen.Authentication.routeId) {
            AuthenticationActivity(navController)
        }

        composable(Screen.MainApp.routeId) {
            MainAppActivity(navController)
        }

        composable(Screen.Rent.routeId) {

            RentScreen(navController = navController, rentViewModel)
        }

        composable(Screen.RentDetail.routeId) {
            RentDetailActivity(navController = navController, rentViewModel)
        }

        composable(Screen.CreateTenant.routeId) {
            CreateTenantScreen(navController, rentViewModel)
        }

        composable(Screen.RentDashbaord.routeId) {
            RentDashboardActivity()
        }

        composable(Screen.Gym.routeId) {
            GymActivity(navController = navController)
        }
        
        composable(Screen.GymUserDetail.routeId) {
            GymUserDetailActivity(navController = navController)
        }

        composable(Screen.Library.routeId) {
            LibraryActivity(navController = navController)
        }

        composable(Screen.LibraryUserDetail.routeId) {
            LibraryUserDetailActivity()
        }

        composable(Screen.Coaching.routeId) {
            CoachingActivity(navController = navController)
        }

        composable(Screen.CoachingUserDetail.routeId) {
            CoachingUserDetailAcitivty()
        }


    }
}




sealed class Screen(val routeId: String) {
    object MainApp: Screen(routeId = "mainApp")
    object Authentication: Screen(routeId = "authentication")
    object Rent: Screen("rent")
    object RentDetail: Screen(routeId = "rentDetail")
    object CreateTenant: Screen(routeId = "createTenant")
    object RentDashbaord: Screen(routeId = "rentDashboard")
    object Coaching: Screen("coaching")
    object CoachingUserDetail: Screen("coachingUserDetail")
    object CreateCoachingUser: Screen("createCoachingUser")
    object Gym: Screen("gym")
    object CreateGymUser: Screen(routeId = "createGymUser")
    object GymUserDetail: Screen(routeId = "gymUserDetail")
    object Library: Screen("library")
    object LibraryUserDetail: Screen("libraryUserDetail")
    object CreateLibraryUser: Screen(routeId = "createLibraryUser")

}


