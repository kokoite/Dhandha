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
import com.example.dhandha.ui.coaching.CoachingScreen
import com.example.dhandha.ui.coaching.detail.CoachingUserDetailAcitivty
import com.example.dhandha.ui.gym.GymScreen
import com.example.dhandha.ui.rent.ui.RentScreen
import com.example.dhandha.ui.rent.ui.create.CreateTenantScreen
import com.example.dhandha.ui.dashboard.RentDashboardActivity
import com.example.dhandha.ui.gym.detail.GymUserDetailActivity
import com.example.dhandha.ui.library.LibraryActivity
import com.example.dhandha.ui.library.detail.LibraryUserDetailActivity
import com.example.dhandha.services.rent.detail.RentDetailActivity
import com.example.dhandha.ui.coaching.create.CreateCoachingUserScreen
import com.example.dhandha.ui.coaching.viewmodel.CoachingViewModel
import com.example.dhandha.ui.gym.create.CreateGymUserScreen
import com.example.dhandha.ui.gym.viewmodel.GymViewModel
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
    val coachingViewModel: CoachingViewModel = hiltViewModel()
    val gymViewModel: GymViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Gym.routeId) {

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
            GymScreen(gymViewModel)
        }

        composable(Screen.CreateGymUser.routeId) {
            CreateGymUserScreen(gymViewModel)
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
            CoachingScreen(viewModel = coachingViewModel)
        }

        composable(Screen.CoachingUserDetail.routeId) {
            CoachingUserDetailAcitivty(coachingViewModel)
        }

        composable(Screen.CreateCoachingUser.routeId) {
            CreateCoachingUserScreen(coachingViewModel)
        }
    }
}


sealed class Screen(val routeId: String) {
    data object MainApp: Screen(routeId = "mainApp")
    data object Authentication: Screen(routeId = "authentication")
    data object Rent: Screen("rent")
    data object RentDetail: Screen(routeId = "rentDetail")
    data object CreateTenant: Screen(routeId = "createTenant")
    data object RentDashbaord: Screen(routeId = "rentDashboard")
    data object Coaching: Screen("coaching")
    data object CoachingUserDetail: Screen("coachingUserDetail")
    data object CreateCoachingUser: Screen("createCoachingUser")
    data object Gym: Screen("gym")
    data object CreateGymUser: Screen(routeId = "createGymUser")
    data object GymUserDetail: Screen(routeId = "gymUserDetail")
    data object Library: Screen("library")
    data object LibraryUserDetail: Screen("libraryUserDetail")
    data object CreateLibraryUser: Screen(routeId = "createLibraryUser")

}


