package com.example.dhandha.botttomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dhandha.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionBottomSheet(onDismiss: () -> Unit) {
    var modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(containerColor = AppTheme.colorScheme.background, onDismissRequest = { onDismiss() }, sheetState = modalBottomSheetState, dragHandle = null) {
         BottomSheetContainer()
    }
}


@Composable
private fun BottomSheetContainer() {
    Box(modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White,
                    containerColor = Color.Black),
                contentPadding = PaddingValues(vertical = 2.dp, horizontal = 20.dp)) {
                Text(text = "Edit Profile ")
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Black) ,
                contentPadding = PaddingValues(vertical = 2.dp, horizontal = 20.dp)
            ) {
                Text(text = "Only rent paid")
            }

            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Black),
                contentPadding = PaddingValues(vertical = 2.dp, horizontal = 20.dp)) {
                Text(text = "Rent and utility paid ")
            }

            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}