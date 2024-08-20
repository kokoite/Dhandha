package com.example.dhandha.ui.authentication

import CustomTextField
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dhandha.Screen
import com.example.dhandha.ui.header.AuthenticationHeader
import com.example.dhandha.helper.NoRippleTheme



@Composable
fun AuthenticationActivity(navController: NavController) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = Modifier
        .fillMaxSize(1f)
        .background(Color(red = 246, green = 246, blue = 255))
        .clickable(interactionSource = interactionSource, indication = null) {
            focusManager.clearFocus()
            keyboardController?.hide()
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .imePadding()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            AuthenticationHeader()
            Spacer(modifier = Modifier.height(30.dp))
            AuthenticationContainer(modifier = Modifier.weight(1f), navController)
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

private fun navigateToMainApp(navController: NavController) {
    navController.navigate(Screen.MainApp.routeId) {


        val route = navController.currentDestination?.route ?: ""
        Log.d("Navigation", "navigateToMainApp: $route, ${navController.currentDestination}")
        popUpTo(route) {
            inclusive = true
        }

        launchSingleTop = true
    }
}

@Composable
private fun AuthenticationContainer(modifier: Modifier, navController: NavController) {
    Card(modifier = modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(1f)
        .clip(RoundedCornerShape(20.dp)), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Spacer(modifier = Modifier.height(20.dp))
        CameraContainer()
        Spacer(modifier = Modifier.height(100.dp))
        NameContainer()
        Spacer(modifier = Modifier.height(30.dp))
        PhoneContainer()
        Spacer(modifier = Modifier.height(30.dp))
        EmailContainer()
        Row(modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 30.dp)
            .fillMaxWidth(1f)
            .background(Color.White), horizontalArrangement = Arrangement.Center) {
            JoinButton(navController)
        }
    }
}

@Composable
private fun CameraContainer() {
    Row(modifier = Modifier
        .padding(horizontal = 12.dp)
        .fillMaxWidth(1f), horizontalArrangement = Arrangement.Center) {
        Image(imageVector = Icons.Default.AddAPhoto.apply {

        }, contentDescription = "Camera", modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .scale(scaleX = -1f, scaleY = 1f), contentScale = ContentScale.Fit)
    }
}

@Composable
private fun NameContainer() {
    val text = remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        CustomTextField(textState = text, placeholderText = "e.g. Pranjal", leadingIcon = Icons.Default.Person)
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(1f)
            .background(Color.LightGray))
    }
}

@Composable
private fun PhoneContainer() {
    val text = remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        CustomTextField(textState = text, placeholderText = "e.g. 8291374655", leadingIcon = Icons.Default.Phone)
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(1f)
            .background(Color.LightGray))
    }
}

@Composable
private fun EmailContainer() {
    val text = remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        CustomTextField(textState = text, placeholderText = "e.g. pranjal@gmail.com", leadingIcon = Icons.Default.Email)
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(1f)
            .background(Color.LightGray))
    }
}

@Composable
private fun JoinButton(navController: NavController) {
    Button(onClick = {
        navigateToMainApp(navController)
    }, contentPadding = PaddingValues(horizontal = 40.dp, vertical = 12.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
        Text(text = "Join Now", style = MaterialTheme.typography.bodyLarge)
    }
}