package com.example.dhandha.ui.customviews

import CustomTextField
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dhandha.helper.showDatePicker
import com.example.dhandha.ui.model.formContainer.FormDateInputContainer
import com.example.dhandha.ui.model.formContainer.FormInputContainer
import com.example.dhandha.ui.theme.AppTheme


@Composable
fun CameraContainer(selectedImageUri: MutableState<Uri?>) {
    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            uri: Uri? ->
        uri?.let {
            selectedImageUri.value = uri
        }
    }
    val imageModifier = remember {
        mutableStateOf(
            Modifier
                .height(200.dp)
                .width(200.dp)
        )
    }

    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .clickable {
            galleryLauncher.launch("image/*")
        }, horizontalArrangement = Arrangement.Center) {

        if(selectedImageUri.value?.toString().isNullOrEmpty()) {
            Image(imageVector = Icons.Default.AddAPhoto, contentDescription = "",
                modifier = imageModifier.value
                    .scale(scaleX = -0.5f, scaleY = 0.5f)
            )
        } else {
            Log.d("TAG", "CameraContainer: ")
            AsyncImage(model = selectedImageUri.value, contentDescription = "", imageModifier.value)
        }
    }
}

@Composable
fun createFormInputContainer(data: List<FormInputContainer>) {
    Row(modifier = Modifier.fillMaxWidth(),  horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        for (item in data) {
            Column (verticalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier.weight(1f)) {
                Text(text = item.labelText, style = AppTheme.typography.placeholder)
                CustomTextField(textState = item.state, placeholderText = item.placeholderText, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createFormDateInputContainer(data: List<FormDateInputContainer>) {
    val shouldShow = remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val selectedContainer = remember {
        mutableStateOf(0)
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        data.withIndex().forEach { (index, item)->
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        shouldShow.value = true
                        selectedContainer.value = index
                    }
            ) {
                Text(text = item.labelText, style = AppTheme.typography.placeholder)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = item.state.value, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray))
            }
        }
    }
    if(shouldShow.value) {
        showDatePicker(state = dateState, shouldShow = shouldShow, textState = data[selectedContainer.value].state)
    }
}