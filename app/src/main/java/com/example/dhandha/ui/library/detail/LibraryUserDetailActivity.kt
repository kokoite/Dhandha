package com.example.dhandha.ui.library.detail

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.dhandha.R
import com.example.dhandha.ui.botttomsheet.ActionBottomSheet
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun LibraryUserDetailActivity() {
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
                UserImageContainer()
                LibraryUserDetailContainer()
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
private fun UserImageContainer() {
    Row(
        Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        Image(painter = painterResource(id = R.drawable.happy_face), contentDescription = "", modifier = Modifier
            .padding(vertical = 20.dp)
            .height(240.dp)
            .width(240.dp)

            .clip(RoundedCornerShape(20.dp)))

    }
}

@Composable
private fun LibraryUserDetailContainer() {

    Card(modifier = Modifier
        .fillMaxWidth(1f)
        .clip(RoundedCornerShape(20.dp))
        , colors = CardDefaults.cardColors(Color.White)) {

        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top  = 20.dp, bottom = 40.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            NameContainer()
            PhoneContainer()
            WeightAndHeightContainer()
            ExistingDiseaseContainer()
            AddressContainer()
            LastPaymentDateAndAmountContainer()
            PlanAndPendingAmountContainer()
            PlanStartAndExpiryDateContainer()
            JoinedDateAndActive()
        }
    }
}

@Composable
private fun NameContainer() {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Name", style = AppTheme.typography.placeholder)
        Text(text = "Pranjal", style = AppTheme.typography.label)
    }
}

@Composable
private fun PhoneContainer() {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Phone", style = AppTheme.typography.placeholder)
        Text(text = "+91 8209131942", style = AppTheme.typography.label)
    }
}


@Composable
private fun AddressContainer() {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Address", style = AppTheme.typography.placeholder)
        Text(text = "Manas hospital", style = AppTheme.typography.label)
    }
}

@Composable
private fun WeightAndHeightContainer() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Weight", style = AppTheme.typography.placeholder)
            Text(text = "80 kgs", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Height", style = AppTheme.typography.placeholder)
            Text(text = "180 cms", style = AppTheme.typography.label)
        }
    }
}

@Composable
private fun ExistingDiseaseContainer() {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Existing diseases", style = AppTheme.typography.placeholder)
        Text(text = "Fracture in left leg", style = AppTheme.typography.label)
    }
}

@Composable
private fun LastPaymentDateAndAmountContainer() {
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
private fun PlanAndPendingAmountContainer() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Plan Amount", style = AppTheme.typography.placeholder)
            Text(text = "₹30000", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Pending Amount", style = AppTheme.typography.placeholder)
            Text(text = "₹8000", style = AppTheme.typography.label)
        }
    }
}

@Composable
private fun PlanStartAndExpiryDateContainer() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Plan start date", style = AppTheme.typography.placeholder)
            Text(text = "20-10-2024", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Plan expiry date", style = AppTheme.typography.placeholder)
            Text(text = "20-12-2024", style = AppTheme.typography.label)
        }
    }
}

@Composable
private fun JoinedDateAndActive() {
    Row(modifier = Modifier.fillMaxWidth(1f), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Joined on", style = AppTheme.typography.placeholder)
            Text(text = "20-10-2024", style = AppTheme.typography.label)
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Active", style = AppTheme.typography.placeholder)
            Text(text = "Active", style = AppTheme.typography.label)
        }
    }
}