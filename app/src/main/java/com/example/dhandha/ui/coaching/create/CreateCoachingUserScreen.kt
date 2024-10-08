package com.example.dhandha.ui.coaching.create

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.example.dhandha.NavControllerCompositionLocal
import com.example.dhandha.R
import com.example.dhandha.data.models.CoachingUser

import com.example.dhandha.helper.getTodayDate
import com.example.dhandha.ui.coaching.viewmodel.CoachingViewModel
import com.example.dhandha.ui.customviews.CameraContainer
import com.example.dhandha.ui.customviews.createFormDateInputContainer
import com.example.dhandha.ui.customviews.createFormInputContainer
import com.example.dhandha.ui.header.GeneralHeader
import com.example.dhandha.ui.model.formContainer.FormDateInputContainer
import com.example.dhandha.ui.model.formContainer.FormInputContainer
import com.example.dhandha.ui.rent.ui.create.CreateButton
import com.example.dhandha.ui.state.UiState
import com.example.dhandha.ui.theme.AppTheme
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun CreateCoachingUserScreen(viewModel: CoachingViewModel) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val focusManager = LocalFocusManager.current
    Box (modifier = Modifier
        .fillMaxSize()
        .background(AppTheme.colorScheme.background)
        .clickable(interactionSource = interactionSource, indication = null) {
            focusManager.clearFocus()
        }){
        Column(modifier = Modifier
            .padding(20.dp)
            .verticalScroll(ScrollState(0)), verticalArrangement = Arrangement.spacedBy(30.dp)) {
            GeneralHeader("Lets Add Students !!", painter = painterResource(id = R.drawable.graduationcap_fill))
            CreateUserContainer(viewModel)
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
private fun CreateUserContainer(viewModel: CoachingViewModel) {
    val todayDate = getTodayDate()
    val context = LocalContext.current
    val contentResolver = remember {
        mutableStateOf(context.contentResolver)
    }
    val flags = remember {
        mutableStateOf(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    val takePermission = remember {
        mutableStateOf(false)
    }

    val controller = NavControllerCompositionLocal.current
    val scope = rememberCoroutineScope()
    val selectedImageUri = remember {
        mutableStateOf<Uri?>(null)
    }
    val nameState = remember {
        mutableStateOf("")
    }

    val phoneState = remember {
        mutableStateOf("")
    }
    val addressState = remember {
        mutableStateOf("")
    }
    val feeState = remember {
        mutableStateOf("")
    }
    val pendingState = remember {
        mutableStateOf("")
    }

    val monthStartDate = remember {
        mutableStateOf(todayDate)
    }
    val monthEndState = remember {
        mutableStateOf(todayDate)
    }
    val lastPaymentAmountState = remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .imePadding()
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)) {
        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally){
            CameraContainer(selectedImageUri)
            val nameData = FormInputContainer("Name","e.g. Pranjal", nameState)
            createFormInputContainer(data = listOf(nameData))
            val phoneData = FormInputContainer("Phone","e.g.8209131942", phoneState)
            createFormInputContainer(data = listOf(phoneData))
            val addressData = FormInputContainer("Address","e.g. Manas hospital", addressState)
            createFormInputContainer(data = listOf(addressData))

            val paidAmount = FormInputContainer("Paid amount","e.g. 20000", lastPaymentAmountState)
            createFormInputContainer(data = listOf(paidAmount))
            val rentData = FormInputContainer("Monthly fees","e.g. 20000", feeState)
            val pendingData = FormInputContainer("Pending Amount", "e.g. 20000", pendingState)
            createFormInputContainer(data = listOf(rentData, pendingData))

            val coachingStartDate = FormDateInputContainer("Coaching Start date", monthStartDate)
            val coachingEndDate = FormDateInputContainer("Coaching End Date", monthEndState)
            createFormDateInputContainer(data = listOf(coachingStartDate, coachingEndDate))

            CreateButton {
                val name = nameState.value
                val phone = phoneState.value
                val address = addressState.value
                val imageUri = selectedImageUri.value
                val fee = feeState.value
                val pending = pendingState.value
                val monthStartDate = monthStartDate.value
                val monthEndDate = monthEndState.value
                val lastPaymentAmount = lastPaymentAmountState.value
                val user = CoachingUser(
                    id = UUID.randomUUID(),
                    name = name,
                    phone = phone,
                    address = address,
                    image = imageUri.toString(),
                    pendingAmount = pending,
                    feesAmount = fee,
                    expiryDate = monthEndDate,
                    startDate = monthStartDate,
                    lastPaymentDate= todayDate,
                    lastPaymentAmount = lastPaymentAmount,
                    joinedDate = todayDate,
                    subjects = listOf(),
                    isActive = true
                )

                viewModel.createUser(user)
                takePermission.value = true
                scope.launch {
                    viewModel.uiState.collect {
                        handleResponse(it, controller)
                    }
                }
            }
            Spacer(modifier = Modifier.height(0.dp))
        }

        if (takePermission.value && selectedImageUri.value != null) {

            selectedImageUri.value?.let {
                contentResolver.value.takePersistableUriPermission(it, flags.value)
            }
            takePermission.value = false
        }
    }
}

private fun handleResponse(uiState: UiState<Boolean>, navController: NavController) {
    when (uiState) {
        is UiState.Loading -> {
            Log.d("TAG", "CreateUserContainer: loading")
        }

        is UiState.Success -> {
            Log.d("TAG", "CreateUserContainer: succeess")
            navController.popBackStack()
        }

        is UiState.Error -> {
            Log.d("TAG", "CreateUserContainer: error")
        }
    }
}



