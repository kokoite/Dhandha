package com.example.dhandha.ui.library

import SearchTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dhandha.R
import com.example.dhandha.Screen
import com.example.dhandha.ui.header.SimpleHeader
import com.example.dhandha.ui.gym.GymUserListCell
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun LibraryActivity(navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    Scaffold(
        floatingActionButton = {
            IconButton(onClick = {
                print("clicked")
            }, modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)) {
                Icon(
                    painterResource(id = R.drawable.book_fill), contentDescription = "", tint = Color.White, modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .clickable {
                            navController.navigate(Screen.CreateTenant.routeId)
                        })
            }
        }

    ) {
        Box(modifier = Modifier
            .padding(it.calculateBottomPadding())
            .fillMaxSize(1f)
            .background(AppTheme.colorScheme.background)
            .clickable(interactionSource, null) {
                focusManager.clearFocus()
            }) {
            Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(30.dp)) {

                HeaderWithSearch(navController)
                LibraryUserDetailContainer(navController)
            }
        }
    }
}

@Composable
private fun HeaderWithSearch(navController: NavController) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SimpleHeader(title = "Welcome back Pranjal!!", painterResource(id = R.drawable.happy_face), action = {
            navController.navigate(route = Screen.RentDashbaord.routeId)
        })
        SearchView()
    }
}

@Composable
private fun SearchView() {
    val text = remember { mutableStateOf("") }
    Box(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .fillMaxWidth(1f)
        .background(Color.White)) {
        SearchTextField( textState = text, placeholderText = "Search you clients here", modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(
                RoundedCornerShape(12.dp)
            ))
    }
}

@Composable
private fun LibraryUserDetailContainer(navController: NavController) {
    Card(modifier = Modifier

        .fillMaxWidth(1f)
        , colors = CardDefaults.cardColors( AppTheme.colorScheme.background)) {

        LazyColumn (verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 6.dp)){
            items(10) {
                LibraryUserListCell() {
                    navController.navigate(Screen.LibraryUserDetail.routeId)
                }
            }
        }
    }
}
