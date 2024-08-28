package com.example.dhandha.ui.library.create

import CustomTextField
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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import coil.compose.rememberImagePainter
import com.example.dhandha.NavControllerCompositionLocal
import com.example.dhandha.R
import com.example.dhandha.data.models.LibraryUser
import com.example.dhandha.helper.getTodayDate
import com.example.dhandha.helper.showDatePicker
import com.example.dhandha.ui.customviews.CameraContainer
import com.example.dhandha.ui.customviews.createFormDateInputContainer
import com.example.dhandha.ui.customviews.createFormInputContainer
import com.example.dhandha.ui.header.GeneralHeader
import com.example.dhandha.ui.library.viewmodel.LibraryViewModel
import com.example.dhandha.ui.model.formContainer.FormDateInputContainer
import com.example.dhandha.ui.model.formContainer.FormInputContainer
import com.example.dhandha.ui.state.UiState
import com.example.dhandha.ui.theme.AppTheme
import java.util.UUID


@Composable
fun CreateLibraryUserScreen(viewModel: LibraryViewModel) {
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
            GeneralHeader("Lets Add Users !!", painter = painterResource(id = R.drawable.book_fill))
            CreateUserContainer(viewModel)
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
private fun CreateUserContainer(viewModel: LibraryViewModel) {
    val todayDate = getTodayDate()
    val shouldObserveState = remember {
        mutableStateOf(false)
    }
    val uiState = viewModel.createUserUiState.collectAsState()
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
    val feesAmountState = remember {
        mutableStateOf("")
    }
    val pendingState = remember {
        mutableStateOf("")
    }

    val startMonthDate = remember {
        mutableStateOf(todayDate)
    }
    val expiryMonthState = remember {
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
            val feesData = FormInputContainer("Fees amount","e.g. 20000", feesAmountState)
            val pendingData = FormInputContainer("Pending Amount", "e.g. 20000", pendingState)
            createFormInputContainer(data = listOf(feesData, pendingData))


            val startMonthData = FormDateInputContainer("Month Start date", startMonthDate)
            val expiryMonthData = FormDateInputContainer("Month End Date", expiryMonthState)
            createFormDateInputContainer(data = listOf(startMonthData, expiryMonthData))

            CreateButton {
                val name = nameState.value
                val phone = phoneState.value
                val address = addressState.value
                val imageUri = selectedImageUri.value
                val fees = feesAmountState.value
                val pending = pendingState.value
                val expiryDate = startMonthDate.value
                val startDate = expiryMonthState.value

                val lastPaymentAmount = lastPaymentAmountState.value

                val user = LibraryUser(
                    id = UUID.randomUUID(),
                    name = name,
                    phone = phone,
                    address = address,
                    image = imageUri.toString(),
                    pendingAmount = pending,
                    feesAmount = fees,
                    expiryDate = startDate,
                    startDate = expiryDate,
                    lastPaymentDate= todayDate,
                    lastPaymentAmount = lastPaymentAmount,
                    joinedDate = todayDate,
                    isActive = true
                )

                viewModel.createUser(user)
                takePermission.value = true
                viewModel.createUser(user)
                shouldObserveState.value = true
            }
            Spacer(modifier = Modifier.height(0.dp))
        }

        if (takePermission.value && selectedImageUri.value != null) {

            selectedImageUri.value?.let {
                contentResolver.value.takePersistableUriPermission(it, flags.value)
            }
            takePermission.value = false
        }

        if(shouldObserveState.value) {
            handleResponse(uiState.value, shouldObserveState, controller)
        }
    }
}


private fun handleResponse(uiState: UiState<Boolean>, shouldObserve: MutableState<Boolean>, navController: NavController) {
    when (uiState) {
        is UiState.Loading -> {
            Log.d("TAG", "CreateUserContainer: loading")
        }

        is UiState.Success -> {
            Log.d("TAG", "CreateUserContainer: succeess")
            shouldObserve.value = false
            navController.popBackStack()
        }

        is UiState.Error -> {
            shouldObserve.value = false
            Log.d("TAG", "CreateUserContainer: error")
        }
    }
}

@Composable
private fun CreateButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }, contentPadding = PaddingValues(horizontal = 30.dp, vertical = 3.dp), modifier = Modifier
        .clip(
            RoundedCornerShape(25.dp)
        )
        .background(Color.Black), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
        Text("Add user", style = AppTheme.typography.buttonText)
    }
}