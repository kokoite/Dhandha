package com.example.dhandha.helper

import android.icu.util.LocaleData
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