package com.example.dhandha.ui.gym

import SearchTextField
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.dhandha.NavControllerCompositionLocal
import com.example.dhandha.R
import com.example.dhandha.Screen
import com.example.dhandha.ui.gym.viewmodel.GymViewModel
import com.example.dhandha.ui.header.SimpleHeader
import com.example.dhandha.ui.state.UiState
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun GymScreen(viewModel: GymViewModel) {
    LaunchedEffect(key1 = null) {
        viewModel.fetchUsers()
    }
    val navController = NavControllerCompositionLocal.current
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
                    painterResource(id = R.drawable.dumbbell_fill), contentDescription = "", tint = Color.White, modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .clickable {
                            navController.navigate(Screen.CreateGymUser.routeId)
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

                HeaderWithSearch(viewModel)
                GymUserContainer(viewModel)
            }
        }
    }
}

@Composable
private fun HeaderWithSearch(viewModel: GymViewModel) {
    val navController = NavControllerCompositionLocal.current
    Column(verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SimpleHeader(title = "Welcome back Pranjal!!", painterResource(id = R.drawable.happy_face), action = {
            navController.navigate(route = Screen.RentDashbaord.routeId)
        })
        SearchView(viewModel)
    }
}

@Composable
private fun SearchView(viewModel: GymViewModel) {
    val text = remember { mutableStateOf("") }
    Box(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .fillMaxWidth(1f)
        .background(Color.White)) {
        SearchTextField( textState = text, placeholderText = "Search you clients here", modifier = Modifier
            .padding(vertical = 8.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )) {
            viewModel.updateSearchQuery(text.value)
        }
    }
}

@Composable
private fun GymUserContainer(viewModel: GymViewModel) {
    val uiState = viewModel.userListState.collectAsState()
    Card(modifier = Modifier

        .fillMaxWidth(1f)
        , colors = CardDefaults.cardColors( AppTheme.colorScheme.background)) {

        when(uiState.value) {
            is UiState.Loading -> {
                Log.d("TAG", "GymUserContainer: loading")
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier
                        .height(40.dp)
                        .width(40.dp),
                        color = Color.Red
                    )
                }
            }

            is UiState.Success -> {
                val response = (uiState.value as UiState.Success).response.collectAsLazyPagingItems()
                val loadState = response.loadState

                LazyColumn (verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 6.dp)){

                    if(loadState.refresh is LoadState.NotLoading) {
                        if(response.itemCount == 0) {
                            item {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White), contentAlignment = Alignment.Center) {
                                    Text(text = "No users found :<__>:", style = AppTheme.typography.placeholder)
                                }
                            }

                        } else {
                            items(response.itemCount, key = response.itemKey { item -> item.id }) {
                                val data = response[it]
                                data?.let {
                                    GymUserListCell(data) {

                                    }
                                }
                            }
                        }
                    }

                    item {
                        if(loadState.append is LoadState.Loading) {
                            Box(modifier = Modifier
                                .fillMaxWidth(), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator(modifier = Modifier
                                    .height(40.dp)
                                    .width(40.dp),
                                    color = Color.Red
                                )
                            }
                        }
                    }
                }
            }
            is UiState.Error -> {

            }
        }
    }
}