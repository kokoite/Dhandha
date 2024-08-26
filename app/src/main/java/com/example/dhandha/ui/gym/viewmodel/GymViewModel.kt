package com.example.dhandha.ui.gym.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.dhandha.data.local.gym.GymUserEntity
import com.example.dhandha.data.models.GymUser
import com.example.dhandha.data.models.GymUserListCell
import com.example.dhandha.data.repository.GymUserRepository
import com.example.dhandha.helper.convertLongDateToString
import com.example.dhandha.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "TAG"

@HiltViewModel
class GymViewModel @Inject constructor(private val repository: GymUserRepository): ViewModel() {


    private var _uiState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val uiState = _uiState

    private var _userListState = MutableStateFlow<UiState<Flow<PagingData<GymUserListCell>>>>(UiState.Loading)
    val userListState = _userListState

    private var job: Job? = null

    private var searchQuery = MutableStateFlow<String>("")

    fun updateSearchQuery(text: String) {
        viewModelScope.launch {
            searchQuery.value = text
            delay(300)
            fetchUsers()
        }
    }

    fun createUser(user: GymUser) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val entity = user.toEntity()
                repository.createUser(entity)
                _uiState.value = UiState.Success(response = true)
            } catch (e: TypeCastException) {
                Log.d(TAG, "fetchUsers: Type converter exception")
            } catch (error: Error) {
                Log.d(TAG, "createUser: ${error.localizedMessage}")
            }
        }
    }

    fun fetchUsers() {
        job?.cancel()
        viewModelScope.launch {
            try {
                _userListState.value = UiState.Loading
                delay(1000)
                val flow = repository.fetchPaginatedUsers(searchQuery.value)
                val updatedFlow = flow.map {
                    it.map {
                        entity: GymUserEntity ->
                        val expiry = convertLongDateToString(entity.planExpiryDate)
                        GymUserListCell(
                            id = entity.id,
                            name = entity.name,
                            plantAmount = entity.planAmount.toString(),
                            phone = entity.phone,
                            pendingAmount = entity.pendingAmount.toString(),
                            expiryDate = expiry,
                            image = entity.image
                        )
                    }
                }
                _userListState.value = UiState.Success(updatedFlow)
            } catch (e: TypeCastException) {
                Log.d(TAG, "fetchUsers: Type converter exception")
            } catch (e: CancellationException) {
                Log.d(TAG, "fetchUsers: cancelled")
            } catch (e: Error) {
                Log.d(TAG, "fetchUsers: ${e.localizedMessage}")
            }
        }
    }

    fun editUser() {

    }

    fun deleteUser() {

    }
}