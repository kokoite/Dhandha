package com.example.dhandha.ui.rent.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dhandha.data.models.CreateRentUser
import com.example.dhandha.data.models.RentUserListCell
import com.example.dhandha.data.repository.RentUserRepository
import com.example.dhandha.ui.state.UiState
import dagger.Binds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(val repository: RentUserRepository) : ViewModel() {

    val TAG = "TAG"
    private var uiState =  MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val _uiState = uiState

    private var userListUiState = MutableStateFlow<UiState<List<RentUserListCell>>>(UiState.Loading)

    fun insertUser(user: CreateRentUser) {
        viewModelScope.launch {
            uiState.emit(UiState.Loading)
            try {
                val entity = user.toEntity()
                repository.createUser(entity)
                uiState.emit(UiState.Success(response = true))
            } catch(e: Error) {
                uiState.emit(UiState.Error("Something went wrong"))
                Log.d(TAG, "insertUser: error ${e.localizedMessage}")
            }
        }
    }

    fun fetchAllUsers() {
        viewModelScope.launch {
            userListUiState.value = UiState.Loading
            try {

            } catch (e: Error) {
                Log.d(TAG, "fetchAllUsers: ${e.localizedMessage}")
                userListUiState.value = UiState.Error("Unable to fetch users")
            }
        }
    }
}