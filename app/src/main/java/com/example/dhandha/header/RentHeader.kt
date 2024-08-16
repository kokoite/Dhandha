package com.example.dhandha.header

import CustomTextField
import SearchTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground =  true, showSystemUi = true)
@Composable
fun RentHeader() {

    Column {
        SimpleHeader()
        Spacer(modifier = Modifier.height(20.dp))
        SearchView()
    }
}

@Composable
fun SearchView() {
    val text = remember { mutableStateOf("") }
    Box(modifier = Modifier.clip(RoundedCornerShape(12.dp)).fillMaxWidth(1f).background(Color.White)) {
       SearchTextField( textState = text, placeholderText = "Search you clients here", modifier = Modifier.padding(vertical = 8.dp).clip(RoundedCornerShape(12.dp)))
    }
}

