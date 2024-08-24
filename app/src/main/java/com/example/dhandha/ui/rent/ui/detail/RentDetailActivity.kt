package com.example.dhandha.services.rent.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dhandha.ui.botttomsheet.ActionBottomSheet
import com.example.dhandha.ui.customviews.Carousel
import com.example.dhandha.ui.rent.viewmodel.RentViewModel
import com.example.dhandha.ui.theme.AppTheme



@Composable
fun RentDetailActivity(navController: NavController, viewModel: RentViewModel) {
    val editButtonClicked = remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            IconButton(onClick = {
                editButtonClicked.value = true
            }, modifier = Modifier
                .padding(bottom = 20.dp, end = 20.dp)
                .clip(CircleShape)
                .background(Color.Black)) {

                Icon(imageVector = Icons.Default.Edit, contentDescription = "", modifier = Modifier
                    .height(20.dp)
                    .width(20.dp), tint = Color.White)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding())
                .fillMaxSize(1f)
                .background(AppTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .verticalScroll(ScrollState(0)),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Spacer(modifier = Modifier.height(0.dp))
                PropertyImageContainer()
                TenantDetailContainer()
                Spacer(modifier = Modifier.height(30.dp))
            }

            if(editButtonClicked.value) {
                ActionBottomSheet {
                    editButtonClicked.value = false
                }
            }
        }
    }
}

@Composable
private fun showBottomSheet() {

}

@Composable
fun PropertyImageContainer() {
    Card (modifier = Modifier
        .fillMaxWidth(1f)
        .height(300.dp)
        .clip(RoundedCornerShape(20.dp)), colors = CardDefaults.cardColors(containerColor = Color.White)){
        Carousel()
    }
}

@Composable
fun TenantDetailContainer() {

    Card(modifier = Modifier
        .fillMaxWidth(1f)
        .clip(RoundedCornerShape(20.dp))
        , colors = CardDefaults.cardColors(Color.White)) {
        
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top  = 20.dp, bottom = 40.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            NameContainer()
            PhoneContainer()
            AddressContainer()
            AdvanceSecurityContainer()
            LastPaymentDateAndAmountContainer()
            RentAndUtilityContainer()
            RentDateStartAndExpiryContainer()
            JoinedDateAndActive()
        }
    }
}

@Composable
fun NameContainer() {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Name", style = AppTheme.typography.placeholder)
        Text(text = "Pranjal", style = AppTheme.typography.label)
    }
}

@Composable
fun PhoneContainer() {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Phone", style = AppTheme.typography.placeholder)
        Text(text = "+91 8209131942", style = AppTheme.typography.label)
    }
}


@Composable
fun AddressContainer() {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Address", style = AppTheme.typography.placeholder)
        Text(text = "Manas hospital", style = AppTheme.typography.label)
    }
}


@Composable
fun AdvanceSecurityContainer() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Advanced Paid", style = AppTheme.typography.placeholder)
            Text(text = "₹20000", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Security Paid", style = AppTheme.typography.placeholder)
            Text(text = "₹8000", style = AppTheme.typography.label)
        }
    }
}

@Composable
fun LastPaymentDateAndAmountContainer() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Last Payment Date", style = AppTheme.typography.placeholder)
            Text(text = "20-10-2024", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Last Paid Amount", style = AppTheme.typography.placeholder)
            Text(text = "₹8000", style = AppTheme.typography.label)
        }
    }
}

@Composable
fun RentAndUtilityContainer() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Rent", style = AppTheme.typography.placeholder)
            Text(text = "₹30000", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Utility", style = AppTheme.typography.placeholder)
            Text(text = "₹8000", style = AppTheme.typography.label)
        }
    }
}

@Composable
fun RentDateStartAndExpiryContainer() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Rent start date", style = AppTheme.typography.placeholder)
            Text(text = "20-10-2024", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Rent expiry date", style = AppTheme.typography.placeholder)
            Text(text = "20-12-2024", style = AppTheme.typography.label)
        }
    }
}

@Composable
fun JoinedDateAndActive() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Started living from", style = AppTheme.typography.placeholder)
            Text(text = "20-10-2024", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Active", style = AppTheme.typography.placeholder)
            Text(text = "Active", style = AppTheme.typography.label)
        }
    }
}