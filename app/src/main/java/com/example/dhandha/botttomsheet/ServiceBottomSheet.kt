package com.example.dhandha.botttomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.ui.theme.AppTheme

data class ServiceBottomSheetViewModel (val painter: Painter, val title: String, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceBottomSheet(onDismiss: () -> Unit) {
    var modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(containerColor = AppTheme.colorScheme.background, onDismissRequest = { onDismiss() }, sheetState = modalBottomSheetState, dragHandle = null) {
        BottomSheetContainer()
    }
}

@Composable
private fun BottomSheetContainer() {
    Box (Modifier.background(Color.White)) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Image(painter = painterResource( id = R.drawable.dumbbell_fill), contentDescription = "", modifier = Modifier
                    .height(40.dp)
                    .width(40.dp), colorFilter = ColorFilter.tint(Color.Red))
                Text(text = "Title for bottom sheet", style = AppTheme.typography.containerTitle)
            }

            Text(text = "This is description of bottom sheets This is description of bottom sheet " +
                    " sThis is description of bottom sheetsThis is " +
                    "description of bott " +
                    "om sheetsThis is description of bottom sheets", style = AppTheme.typography.label)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(onClick = { /*TODO*/ }, Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                    Text(text = "Subscribe Now ")
                }

                Button(onClick = { /*TODO*/ }, Modifier.weight(1f), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                    Text(text = "Subscribe later")
                }
            }

        }
    }
}

