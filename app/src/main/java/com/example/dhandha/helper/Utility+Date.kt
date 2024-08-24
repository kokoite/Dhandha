package com.example.dhandha.helper

import android.icu.util.LocaleData
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

const val dateFormat = "dd-MM-yyyy"

fun convertLongDateToString(date: Long) : String {
    val reqDate = Date(date)
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    return formatter.format(reqDate)
}

fun convertStringDateToLong(date: String): Long? {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    val reqDate = formatter.parse(date)
    return reqDate?.time
}

fun getTodayDate(): String {
    val date = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern(dateFormat)
    return date.format(formatter)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDatePicker(state: DatePickerState, shouldShow: MutableState<Boolean>, textState: MutableState<String>) {
    DatePickerDialog(
        onDismissRequest = {
            shouldShow.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                textState.value = convertLongDateToString(state.selectedDateMillis ?: 0)
                shouldShow.value = false
            }) {
                Text(text = "Confirm")
            }
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