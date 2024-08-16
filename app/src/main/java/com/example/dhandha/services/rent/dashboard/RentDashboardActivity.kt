package com.example.dhandha.services.rent.dashboard

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dhandha.R
import com.example.dhandha.header.GeneralHeader
import com.example.dhandha.ui.theme.AppTheme

@Composable
fun RentDashboardActivity() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(AppTheme.colorScheme.background)) {
        Column(modifier = Modifier
            .padding(20.dp)
            .verticalScroll(ScrollState(0)), verticalArrangement = Arrangement.spacedBy(30.dp)) {
            GeneralHeader(title = "Know your Income",painterResource(id = R.drawable.house_fill))
            EarningContainer()
            TransactionContainer()
        }
    }
}

@Composable
fun EarningContainer() {
    Card(modifier = Modifier
        .fillMaxWidth(1f)
        .height(200.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier
            .padding(12.dp)
            .fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(text = "Your earned ₹1000000", style = AppTheme.typography.headerTitle)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "From July 2018 To July 2024", style = AppTheme.typography.label)
            Spacer(modifier = Modifier.height(30.dp))
            Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black), modifier = Modifier.weight(1f)) {
                    Text(text = "Start Date", style = AppTheme.typography.buttonText)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black), modifier = Modifier.weight(1f)) {
                    Text(text = "End date", style = AppTheme.typography.buttonText)
                }
            }
        }
    }
}

@Composable
fun TransactionContainer() {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .fillMaxWidth(1f)
        .height(400.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxSize()) {
            Text(text = "Recent Transactions", style = AppTheme.typography.headerTitle, modifier = Modifier.padding(horizontal = 12.dp))

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(modifier = Modifier
                .padding(top = 4.dp)
                .weight(1f), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                items(6) {
                    Column {
                        TransactionCell()
                        Spacer(modifier = Modifier
                            .height(0.4.dp)
                            .fillMaxWidth()
                            .background(Color.Gray))
                        if(it == 5) {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun TransactionCell() {
    Card(modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .fillMaxWidth()
        .height(120.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize(),verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.cash), contentDescription = "", modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(
                    CircleShape
                ))
            Spacer(modifier = Modifier.width(30.dp))
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "₹10000", style = AppTheme.typography.headerTitle)
                Text(text = "Paid By Pranjal Agarwal", style =  AppTheme.typography.label)
                Text(text = "On 20-07-2024", style = AppTheme.typography.label)

            }
        }
    }
}