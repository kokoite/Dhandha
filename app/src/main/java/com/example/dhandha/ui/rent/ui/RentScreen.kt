package com.example.dhandha.ui.rent.ui

import SearchTextField
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.dhandha.NavControllerCompositionLocal
import com.example.dhandha.R
import com.example.dhandha.Screen
import com.example.dhandha.ui.header.SimpleHeader
import com.example.dhandha.ui.rent.viewmodel.RentViewModel
import com.example.dhandha.ui.state.UiState
import com.example.dhandha.ui.theme.AppTheme
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey


const val TAG = "TAG"

@Composable
fun RentScreen(navController: NavController, viewModel: RentViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = null) {
        viewModel.fetchAllUsers()
    }

    Scaffold(
        floatingActionButton = {
            IconButton(onClick = {
               print("clicked")
            }, modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)) {
                Icon(painter = painterResource(id = R.drawable.house_fill), contentDescription = "", tint = Color.White, modifier = Modifier
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
                RentUserContainer(viewModel)
            }
        }
    }

}

@Composable
private fun HeaderWithSearch(navController: NavController) {
    Column {
        SimpleHeader(title = "Welcome back Pranjal!!", painterResource(id = R.drawable.happy_face), action = {
            navController.navigate(route = Screen.RentDashbaord.routeId)
        })
        Spacer(modifier = Modifier.height(20.dp))
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
private fun RentUserContainer(viewModel: RentViewModel) {

    val navController = NavControllerCompositionLocal.current
    val uiState = viewModel.userListUiState.collectAsState()

    Card(modifier = Modifier
        .fillMaxWidth(1f)
        .fillMaxHeight()
    , colors = CardDefaults.cardColors( AppTheme.colorScheme.background)) {

        when(uiState.value) {
            is UiState.Loading -> {

                Log.d(TAG, "RentUserContainer: Loading users")
            }

            is UiState.Success -> {
                Log.d(TAG, "RentUserContainer: Success")
                val response = (uiState.value as UiState.Success).response.collectAsLazyPagingItems()
                Log.d(TAG, "RentUserContainer: ${response.itemCount}")
                LazyColumn (verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 6.dp)){
                    items(count = response.itemCount, key = response.itemKey { it.id }, contentType = response.itemContentType { "content-type"}) {
                        val data = response[it]
                        data?.let {
                            RentUserListCell(data) {
                                navController.navigate(Screen.RentDetail.routeId)
                            }
                        }

                    }
                }
            }

            is UiState.Error -> {
                Log.d(TAG, "RentUserContainer: Error")
            }
        }
    }
}