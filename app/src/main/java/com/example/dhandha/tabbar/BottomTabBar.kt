package com.example.dhandha.tabbar

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dhandha.R

@Composable
fun BottomTabBar(navController: NavController) {
    val items = listOf(NavigationItemViewModel.HomeScreeNavigation, NavigationItemViewModel.ProfileScreeNavigation)

    val selectedIndex = remember { mutableStateOf(0) }


    NavigationBar(containerColor = Color.White, modifier = Modifier.height(70.dp)) {
        items.forEachIndexed {index, item ->
            val tintColor = if (index == selectedIndex.value) {
                Color.Red
            } else {
                Color.Gray
            }

            val labelStyle = MaterialTheme.typography.headlineMedium
            NavigationBarItem(
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index

                    navController.navigate(item.routeId) {
                        navController.graph.startDestinationRoute?.let { route ->
                            Log.d("NavigationRouting", "BottomTabBar: $route")
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
            },
                icon = { Icon(painterResource(id = item.iconId),contentDescription = item.title, tint = tintColor) },
                label = {
                    Text(text = item.title, style = MaterialTheme.typography.titleSmall, color = Color.Black)
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
            )
        }
    }
}


sealed class NavigationItemViewModel (val title: String, val iconId: Int, val routeId: String) {
    object HomeScreeNavigation: NavigationItemViewModel("Home", R.drawable.house, routeId = "home"  )
    object ProfileScreeNavigation: NavigationItemViewModel("Profile", iconId = R.drawable.person, routeId = "profile")
}