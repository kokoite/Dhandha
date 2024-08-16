package com.example.dhandha.services.rent

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dhandha.Screen
import com.example.dhandha.header.RentHeader
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun RentActivity(navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    Scaffold(

        floatingActionButton = {
            IconButton(onClick = {
               print("clicked")
            }, modifier = Modifier.clip(CircleShape).background(Color.Black)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White, modifier = Modifier.height(20.dp).width(20.dp).clickable {
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

                RentHeader()
                RentUserContainer(navController)
            }
        }
    }

}


@Composable
fun RentUserContainer(navController: NavController) {
    Card(modifier = Modifier

        .fillMaxWidth(1f)
        , colors = CardDefaults.cardColors( AppTheme.colorScheme.background)) {

        LazyColumn (verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 6.dp)){
            items(listOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1)) {
                UserListCell() {
                    navController.navigate(Screen.RentDetail.routeId)
                }
            }
        }

    }
}