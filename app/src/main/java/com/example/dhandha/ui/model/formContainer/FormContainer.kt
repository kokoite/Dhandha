package com.example.dhandha.ui.model.formContainer

import androidx.compose.runtime.MutableState

data class FormInputContainer(
    val labelText: String,
    val placeholderText: String,
    val state: MutableState<String>,
    val onValueChanged: (()->Unit)? = null
)

data class FormDateInputContainer(
    val labelText: String,
    val state: MutableState<String>
)