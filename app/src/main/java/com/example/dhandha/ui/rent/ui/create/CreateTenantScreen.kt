package com.example.dhandha.ui.rent.ui.create

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
import coil.compose.rememberImagePainter
import com.example.dhandha.NavControllerCompositionLocal
import com.example.dhandha.R
import com.example.dhandha.data.models.RentUser
import com.example.dhandha.helper.getTodayDate
import com.example.dhandha.helper.showDatePicker
import com.example.dhandha.ui.header.GeneralHeader
import com.example.dhandha.ui.rent.viewmodel.RentViewModel
import com.example.dhandha.ui.state.UiState
import com.example.dhandha.ui.theme.AppTheme
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun CreateTenantScreen(navController: NavController, viewModel: RentViewModel) {
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
            GeneralHeader("Lets Add Tenants !!", painter = painterResource(id = R.drawable.house_fill))
            CreateUserContainer(viewModel)
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
fun CreateUserContainer(viewModel: RentViewModel) {
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
    val rentState = remember {
        mutableStateOf("")
    }
    val pendingState = remember {
        mutableStateOf("")
    }
    val agreementStartState = remember {
        mutableStateOf(todayDate)
    }

    val agreementEndState = remember {
        mutableStateOf(todayDate)
    }
    val advanceState = remember {
        mutableStateOf("")
    }
    val securityState = remember {
        mutableStateOf("")
    }
    val rentStartState = remember {
        mutableStateOf(todayDate)
    }
    val rentEndState = remember {
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
            val advanceData = FormInputContainer("Advance amount","e.g. 20000", advanceState)
            val securityData = FormInputContainer("Security Amount", "e.g. 20000", securityState)
            createFormInputContainer(data = listOf(advanceData, securityData))
            val paidAmount = FormInputContainer("Paid amount","e.g. 20000", lastPaymentAmountState)
            createFormInputContainer(data = listOf(paidAmount))
            val rentData = FormInputContainer("Rent amount","e.g. 20000", rentState)
            val pendingData = FormInputContainer("Pending Amount", "e.g. 20000", pendingState)
            createFormInputContainer(data = listOf(rentData, pendingData))
            
            val agreementStartData = FormDateInputContainer("Agreement Start Date", agreementStartState)
            val agreementEndData = FormDateInputContainer("Agreement End Date", agreementEndState)
            createFormDateInputContainer(data = listOf(agreementStartData, agreementEndData))

            val rentStartData = FormDateInputContainer("Rent Start date", rentStartState)
            val rentEndData = FormDateInputContainer("Rent End Date", rentEndState)
            createFormDateInputContainer(data = listOf(rentStartData, rentEndData))

            PropertyImageButton()
            CreateButton {
                val name = nameState.value
                val phone = phoneState.value
                val address = addressState.value
                val imageUri = selectedImageUri.value
                val rent = rentState.value
                val pending = pendingState.value
                val rentStart = rentStartState.value
                val rentEnd = rentEndState.value
                val agreementStart = agreementStartState.value
                val agreementEnd = agreementEndState.value
                val lastPaymentAmount = lastPaymentAmountState.value
                val advance = advanceState.value
                val security = securityState.value
                val user = RentUser(
                    id = UUID.randomUUID(),
                    name = name,
                    phone = phone,
                    address = address,
                    image = imageUri.toString(),
                    advance = advance,
                    agreementEnd = agreementEnd,
                    agreementStart = agreementStart,
                    pending = pending,
                    rent = rent,
                    rentEnd = rentEnd,
                    rentStart = rentStart,
                    security = security,
                    lastPaymentDate= todayDate,
                    paidAmount = lastPaymentAmount,
                    joinedDate = todayDate
                )

                viewModel.insertUser(user)
                takePermission.value = true
                scope.launch {
                    viewModel.createUserState.collect {
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

@Composable
fun CameraContainer(selectedImageUri: MutableState<Uri?>) {
    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? ->
        uri?.let {
            selectedImageUri?.value = uri
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
            Image(painter = rememberImagePainter(data = selectedImageUri.value), contentDescription = "", imageModifier.value)
        }
    }
}

@Composable
private fun createFormInputContainer(data: List<FormInputContainer>) {
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
private fun createFormDateInputContainer(data: List<FormDateInputContainer>) {
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

@Composable
fun PropertyImageButton() {
    Button(onClick = { /*TODO*/ }, contentPadding = PaddingValues(horizontal = 20.dp, vertical = 3.dp), modifier = Modifier
        .clip(
            RoundedCornerShape(25.dp)
        )
        .background(Color.Black), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
        Text("Want to add property documents?", style = AppTheme.typography.buttonText)
    }
}

@Composable
fun CreateButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }, contentPadding = PaddingValues(horizontal = 30.dp, vertical = 3.dp), modifier = Modifier
        .clip(
            RoundedCornerShape(25.dp)
        )
        .background(Color.Black), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
        Text("Add user", style = AppTheme.typography.buttonText)
    }
}

data class FormInputContainer(
    val labelText: String,
    val placeholderText: String,
    val state: MutableState<String>
)

data class FormDateInputContainer(
    val labelText: String,
    val state: MutableState<String>
)