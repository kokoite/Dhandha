package com.example.dhandha.services.rent.create

import CustomTextField
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
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.dhandha.header.GeneralHeader
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun CreateTenantActivity() {
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
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.Center) {
        Image(imageVector = Icons.Default.AddAPhoto, contentDescription = "", modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .scale(scaleX = -1f, scaleY = 1f))
    }
}

@Composable
fun NameContainer() {
    val text = remember { mutableStateOf("") }
    Column (verticalArrangement = Arrangement.spacedBy(2.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CustomTextField(textState = text, placeholderText = "e.g. Pranjal Agarwal", leadingIcon = Icons.Default.Person, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray))
    }
}

@Composable
fun PhoneContainer() {
    val text = remember { mutableStateOf("") }
    Column (verticalArrangement = Arrangement.spacedBy(2.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CustomTextField(textState = text, placeholderText = "e.g. 8209131942", leadingIcon = Icons.Default.Phone, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray))
    }
}

@Composable
fun AddressContainer() {
    val text = remember { mutableStateOf("") }
    Column (verticalArrangement = Arrangement.spacedBy(2.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CustomTextField(textState = text, placeholderText = "e.g. Manas hospital", leadingIcon = Icons.Default.AddLocationAlt, modifier = Modifier.fillMaxWidth())
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            CustomTextField(
                textState = text1,
                placeholderText = "Advance Amount",
                leadingIcon = Icons.Default.CurrencyRupee,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            CustomTextField(
                textState = text1,
                placeholderText = "Security Amount",
                leadingIcon = Icons.Default.CurrencyRupee,
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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            CustomTextField(
                textState = text1,
                placeholderText = "Rent Amount",
                leadingIcon = Icons.Default.CurrencyRupee,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            CustomTextField(
                textState = text1,
                placeholderText = "Utility Amount",
                leadingIcon = Icons.Default.CurrencyRupee,
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
fun AgreementStartAndEndContainer() {
    val text1 = remember { mutableStateOf("") }
    val text2 = remember { mutableStateOf("") }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(20.dp)){
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            CustomTextField(
                textState = text1,
                placeholderText = "Rent Amount",
                leadingIcon = Icons.Default.DateRange,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.LightGray))
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            CustomTextField(
                textState = text1,
                placeholderText = "Utility Amount",
                leadingIcon = Icons.Default.DateRange,
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
fun PropertyImageButton() {
    Button(onClick = { /*TODO*/ }, contentPadding = PaddingValues(horizontal = 20.dp, vertical = 3.dp), modifier = Modifier
        .clip(
            RoundedCornerShape(25.dp)
        )
        .background(Color.Black), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
        Text("Want to add property images?", style = AppTheme.typography.buttonText)
    }
}


@Composable
fun CreateButton() {
    Button(onClick = { /*TODO*/ }, contentPadding = PaddingValues(horizontal = 20.dp, vertical = 3.dp), modifier = Modifier
        .clip(
            RoundedCornerShape(25.dp)
        )
        .background(Color.Black), colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
        Text("Add user", style = AppTheme.typography.buttonText)
    }
}