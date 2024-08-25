package com.example.dhandha.ui.rent.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dhandha.data.models.CreateRentUser
import com.example.dhandha.data.models.RentUserListCell
import com.example.dhandha.data.repository.RentUserRepository
import com.example.dhandha.ui.state.UiState
import dagger.Binds
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(val repository: RentUserRepository) : ViewModel() {

    val TAG = "TAG"
    private var uiState =  MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val _uiState = uiState

    private var _userListUiState = MutableStateFlow<UiState<Flow<PagingData<RentUserListCell>>>>(UiState.Loading)
    val userListUiState = _userListUiState

    private var job: Job? = null

    private var _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery


    fun updateSearchQuery(text: String) {
        _searchQuery.value = text
    }


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

        job?.cancel()
        job = viewModelScope.launch {
            userListUiState.value = UiState.Loading
            delay(1000)
            try {
                val response = repository.getAllUsers(searchQuery.value)
                Log.d(TAG, "fetchAllUsers: $response")
                _userListUiState.value = UiState.Success(response)
            } catch (e: Error) {
                Log.d(TAG, "fetchAllUsers: ${e.localizedMessage}")
                _userListUiState.value = UiState.Error("Unable to fetch users")
            }
        }
    }
}