package com.example.dhandha.ui.coaching.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.dhandha.data.models.CoachingUser
import com.example.dhandha.data.models.CoachingUserListCell
import com.example.dhandha.data.repository.CoachingUserRepository
import com.example.dhandha.helper.convertLongDateToString
import com.example.dhandha.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CoachingViewModel @Inject constructor(private val repository: CoachingUserRepository): ViewModel() {

    private var _uiState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val uiState = _uiState

    private var fetchAllUserJob: Job? = null
    private var searchQuery = MutableStateFlow<String>("")
    private var _userListState = MutableStateFlow<UiState<Flow<PagingData<CoachingUserListCell>>>>(UiState.Loading)
    val userListState = _userListState

    fun createUser(user: CoachingUser) {
        viewModelScope.launch {
            try {
                val entity = user.toEntity()
                repository.createUser(entity)
                _uiState.value = UiState.Success(true)
                Log.d("TAG", "createCoachingUser: Success")
            } catch (error: Error) {
                Log.d("TAG", "createCoachingUser: Failed")
            }
        }
    }

    fun updateSearchQuery(text: String) {
        viewModelScope.launch {
            searchQuery.value = text
            delay(100)
            fetchPaginatedUsers()
        }
    }

    fun fetchPaginatedUsers() {
        fetchAllUserJob?.cancel()
        fetchAllUserJob = viewModelScope.launch {
            _userListState.value = UiState.Loading
            delay(1000)
            try {
                if(!isActive) {
                    Log.d("TAG", "fetchPaginatedUsers: cancelled")
                    return@launch
                } else {
                    val flow = repository.fetchPaginatedUser(searchQuery.value)
                    Log.d("TAG", "fetchPaginatedUsers ${searchQuery}")
                    val response = flow.map {
                        it.map {entity ->
                            val expiry = convertLongDateToString(entity.monthExpiryDate)
                            CoachingUserListCell(
                                expiryDate = expiry, image = entity.image,
                                pendingAmount = entity.pending.toString(), phone = entity.phone, feesAmount = entity.fees.toString(), name = entity.name, id = entity.id
                            )
                        }
                    }.cachedIn(viewModelScope)
                    _userListState.value = UiState.Success(response)
                }
            } catch (exception: CancellationException) {
                Log.d("TAG", "fetchPaginatedUsers: Cancelled catch")
                _userListState.value = UiState.Error("Something went wrong")
            }

            catch (error: Error) {
                Log.d("TAG", "fetchPaginatedUsers: Error ${error.localizedMessage}")
                _userListState.value = UiState.Error("${error.localizedMessage}")
            }
        }
    }


    fun fetchUserDetail() {

    }

    fun editUser() {

    }

    fun deleteUser() {

    }
}