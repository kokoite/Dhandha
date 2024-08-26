package com.example.dhandha.ui.coaching

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.dhandha.NavControllerCompositionLocal
import com.example.dhandha.R
import com.example.dhandha.Screen
import com.example.dhandha.ui.coaching.viewmodel.CoachingViewModel
import com.example.dhandha.ui.header.SimpleHeader
import com.example.dhandha.ui.state.UiState
import com.example.dhandha.ui.theme.AppTheme
import kotlinx.coroutines.flow.asStateFlow


const val TAG = "TAG"

@Composable
fun CoachingScreen(viewModel: CoachingViewModel) {
    val navController = NavControllerCompositionLocal.current
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    LaunchedEffect(key1 = null) {
        viewModel.fetchPaginatedUsers()
    }
    Scaffold(
        floatingActionButton = {
            IconButton(onClick = {
                print("clicked")
            }, modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)) {
                Icon(
                    painterResource(id = R.drawable.graduationcap_fill), contentDescription = "", tint = Color.White, modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .clickable {
                            navController.navigate(Screen.CreateCoachingUser.routeId)
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
                CoachingUserContainer(viewModel)
            }
        }
    }
}

@Composable
private fun HeaderWithSearch(viewModel: CoachingViewModel) {
    val navController = NavControllerCompositionLocal.current
    Column(verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SimpleHeader(title = "Welcome back Pranjal!!", painterResource(id = R.drawable.happy_face), action = {
            navController.navigate(route = Screen.RentDashbaord.routeId)
        })
        SearchView(viewModel)
    }
}

@Composable
private fun SearchView(viewModel: CoachingViewModel) {
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
private fun CoachingUserContainer(viewModel: CoachingViewModel) {

    val navController = NavControllerCompositionLocal.current
    val response = viewModel.userListState.collectAsState()
    val lazyState = rememberLazyListState()
    Card(modifier = Modifier

        .fillMaxWidth(1f)
        , colors = CardDefaults.cardColors( AppTheme.colorScheme.background)) {

        when(response.value) {

            is UiState.Loading -> {
                Log.d(TAG, "CoachingUserContainer: Loading")
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

                val users = (response.value as UiState.Success).response.collectAsLazyPagingItems()
                val loadState = users.loadState
                when(loadState.append) {
                    is LoadState.Loading -> {
                        Log.d(TAG, "CoachingUserContainer: loading ${users.itemCount}")
                    }

                    is LoadState.NotLoading -> {
                        Log.d(TAG, "CoachingUserContainer: not loading ${users.itemCount}")
                    }

                    is LoadState.Error -> {
                        Log.d(TAG, "CoachingUserContainer: appending error ${users.itemCount}")
                    }
                }

                when (loadState.refresh) {

                    is LoadState.Loading -> {

                    }

                    is LoadState.NotLoading -> {
                        if(users.itemCount == 0) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White), contentAlignment = Alignment.Center) {
                                Text(
                                    text = "No users found :(",
                                    style = AppTheme.typography.placeholder,
                                    fontSize = 23.sp
                                )
                            }
                        } else {
                            LazyColumn(state = lazyState) {
                                items(users.itemCount, key = users.itemKey { it.id}) {
                                    val data = users[it]
                                    data?.let {
                                        CoachingUserListCell(user = data) {

                                        }
                                    }
                                }
                            }
                        }

                    }

                    is LoadState.Error -> {
                        Log.d(TAG, "CoachingUserContainer: refresh error ${users.itemCount}")
                    }
                }
            }

            is UiState.Error -> {
                Log.d(TAG, "CoachingUserContainer: Main error show box with retry button")
            }
        }
    }
}
