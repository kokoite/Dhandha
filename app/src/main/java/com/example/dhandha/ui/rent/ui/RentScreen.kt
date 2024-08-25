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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
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

                HeaderWithSearch(navController, viewModel)
                RentUserContainer(viewModel)
            }
        }
    }

}

@Composable
private fun HeaderWithSearch(navController: NavController, viewModel: RentViewModel) {
    Column {
        SimpleHeader(title = "Welcome back Pranjal!!", painterResource(id = R.drawable.happy_face), action = {
            navController.navigate(route = Screen.RentDashbaord.routeId)
        })
        Spacer(modifier = Modifier.height(20.dp))
        SearchView(viewModel)
    }
}

@Composable
private fun SearchView(viewModel: RentViewModel) {
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
            viewModel.fetchAllUsers()
        }
    }
}

@Composable
private fun RentUserContainer(viewModel: RentViewModel) {

    val navController = NavControllerCompositionLocal.current
    val uiState = viewModel.userListUiState.collectAsState()
    val columnState = rememberLazyListState()


    Card(modifier = Modifier
        .fillMaxWidth(1f)
        .fillMaxHeight()
    , colors = CardDefaults.cardColors( AppTheme.colorScheme.background)) {

        when(uiState.value) {
            is UiState.Loading -> {
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
                Log.d(TAG, "RentUserContainer: Success ${response.itemCount}")
                LazyColumn (state = columnState, verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 6.dp)){
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
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)) {

                    Text(text = "Oops somthing went wrong. Please retry", textAlign = TextAlign.Center)
                }
                Log.d(TAG, "RentUserContainer: Error")
            }
        }
    }
}