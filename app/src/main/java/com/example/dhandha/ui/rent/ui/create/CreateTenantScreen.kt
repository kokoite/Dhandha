package com.example.dhandha.ui.rent.ui.create

import CustomTextField
import android.net.Uri
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.ui.header.GeneralHeader
import com.example.dhandha.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun CreateTenantScreen() {
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
            CreateUserContainer()
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
fun CreateUserContainer() {
    Box(modifier = Modifier
        .imePadding()
        .fillMaxWidth()
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)) {
        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally){

            CameraContainer()
            Spacer(modifier = Modifier.height(40.dp))
            NameContainer()
            PhoneContainer()
            AddressContainer()
            AdvanceAndSecurityContainer()
            RentAndUtilityContainer()
            AgreementStartAndEndContainer()
            PropertyImageButton()
            CreateButton()
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Composable
fun CameraContainer() {


    val selectedImageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? ->
        selectedImageUri?.value = uri
    }
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .clickable {
            galleryLauncher.launch("image/*")
        }, horizontalArrangement = Arrangement.Center) {
        Image(imageVector = Icons.Default.AddAPhoto, contentDescription = "", modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .scale(scaleX = -1f, scaleY = 1f))
    }

}


@Composable
fun NameContainer() {
    val text = remember { mutableStateOf("") }
    Column (verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(text = "Name", style = AppTheme.typography.placeholder)
        CustomTextField(textState = text, placeholderText = "e.g. Pranjal Agarwal", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray))
    }
}

@Composable
fun PhoneContainer() {
    val text = remember { mutableStateOf("") }
    Column (verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(text = "Phone", style = AppTheme.typography.placeholder)
        CustomTextField(textState = text, placeholderText = "e.g. 8209131942", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray))
    }
}

@Composable
fun AddressContainer() {
    val text = remember { mutableStateOf("") }
    Column (verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(text = "Address", style = AppTheme.typography.placeholder)
        CustomTextField(textState = text, placeholderText = "e.g. Manas hospital", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray))
    }
}


@Composable
fun AdvanceAndSecurityContainer() {
    val text1 = remember { mutableStateOf("") }
    val text2 = remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Advance Amount", style = AppTheme.typography.placeholder)
            CustomTextField(
                textState = text1,
                placeholderText = "e.g. 5000",

                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),

            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Security Amount", style = AppTheme.typography.placeholder)
            CustomTextField(
                textState = text2,
                placeholderText = "e.g. 5000",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }
    }
}

@Composable
fun RentAndUtilityContainer() {
    val text1 = remember { mutableStateOf("") }
    val text2 = remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)){
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Rent Amount", style = AppTheme.typography.placeholder)
            CustomTextField(
                textState = text1,
                placeholderText = "e.g. 20000",

                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Utility Amount", style = AppTheme.typography.placeholder)
            CustomTextField(
                textState = text2,
                placeholderText = "e.g. 2000",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgreementStartAndEndContainer() {
    val shouldShow = remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val state1 = remember { mutableStateOf("") }
    val state2 = remember { mutableStateOf("") }
    val startOrEndSelected = remember {
        // Here true indicates start selected
        mutableStateOf(true)
    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)){
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Agreement Start date", style = AppTheme.typography.placeholder)
            CustomTextField(
                isEditable = false,
                textState = state1,
                placeholderText = "e.g. 2000",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        shouldShow.value = true
                        startOrEndSelected.value = true
                    }
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))

        }

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Agreement end date", style = AppTheme.typography.placeholder)
            CustomTextField(
                isEditable = false,
                textState = state2,
                placeholderText = "e.g. 2000",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        shouldShow.value = true
                        startOrEndSelected.value = false
                    }
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }
    }

    if(shouldShow.value) {
        if(startOrEndSelected.value) {
            showDatePicker(dateState, shouldShow, state1)
        } else {
            showDatePicker(dateState, shouldShow, state2)
        }


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
fun CreateButton() {
    Button(onClick = { /*TODO*/ }, contentPadding = PaddingValues(horizontal = 30.dp, vertical = 3.dp), modifier = Modifier
        .clip(
            RoundedCornerShape(25.dp)
        )
        .background(Color.Black), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
        Text("Add user", style = AppTheme.typography.buttonText)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun showDatePicker(state: DatePickerState, shouldShow: MutableState<Boolean>, textState: MutableState<String>) {
    DatePickerDialog(
        onDismissRequest = {
            shouldShow.value = false
        },
        confirmButton = {
            Text(text = "Confirm", Modifier.clickable {
                state.selectedDateMillis?.let {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = it
                    val formatter = SimpleDateFormat("dd-MM-yyyy")
                    val date = formatter.format(calendar)
                    textState.value = date
                }
                shouldShow.value = false
            })
        },
        colors = DatePickerDefaults.colors(Color.White),
    ) {
        DatePicker(state = state, title = null, headline = null, showModeToggle = false, colors = DatePickerDefaults.colors(

            containerColor = Color.White,
            dayContentColor = Color.Black,
            selectedDayContainerColor = Color.Black,
            selectedDayContentColor = Color.White,
            weekdayContentColor = Color.Black,
            todayDateBorderColor = Color.Black,
            todayContentColor = Color.Black,
            yearContentColor = Color.Black,
            currentYearContentColor = Color.Black,
            selectedYearContentColor = Color.White,
            selectedYearContainerColor = Color.Black
        ))
    }
}