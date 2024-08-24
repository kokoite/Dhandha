package com.example.dhandha.ui.state

sealed class UiState <out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val response: T): UiState<T>()
    data class Error(val error: String): UiState<Nothing>()
}