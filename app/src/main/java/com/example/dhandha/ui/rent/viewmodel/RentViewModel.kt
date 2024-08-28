package com.example.dhandha.ui.rent.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.room.paging.util.queryItemCount
import com.example.dhandha.data.models.RentUser
import com.example.dhandha.data.models.RentUserListCell
import com.example.dhandha.data.repository.RentUserRepository
import com.example.dhandha.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(private val repository: RentUserRepository) : ViewModel() {

    val TAG = "TAG"
    private var _createUserState =  MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val createUserState = _createUserState

    private var _userListUiState = MutableStateFlow<UiState<Flow<PagingData<RentUserListCell>>>>(UiState.Loading)
    val userListUiState: StateFlow<UiState<Flow<PagingData<RentUserListCell>>>> = _userListUiState

    private var _userDetailUiState =  MutableStateFlow<UiState<RentUser>>(UiState.Loading)
    val userDetailUiState: StateFlow<UiState<RentUser>> = _userDetailUiState


    private var job: Job? = null
    private var _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery

    private var selectedId: UUID? = null

    fun updateSelectedId(id: UUID) {
        selectedId = id
    }

    fun updateSearchQuery(text: String) {
        job?.cancel()
        job = viewModelScope.launch {
            _searchQuery.value = text
            delay(200)
            if(isActive) {
                fetchAllUsers()
            }
        }
    }

    fun insertUser(user: RentUser) {
        viewModelScope.launch {
            _createUserState.emit(UiState.Loading)
            try {
                val entity = user.toEntity()
                repository.createUser(entity)
                _createUserState.emit(UiState.Success(response = true))
            } catch(e: Error) {
                _createUserState.emit(UiState.Error("Something went wrong"))
                Log.d(TAG, "insertUser: error ${e.localizedMessage}")
            }
        }
    }

    fun fetchAllUsers() {
        viewModelScope.launch {
            _userListUiState.value = UiState.Loading
            delay(1000)
            try {
                val response = repository.getAllUsers(searchQuery.value).cachedIn(viewModelScope)
                Log.d(TAG, "fetchAllUsers: $response")
                _userListUiState.value = UiState.Success(response)
            } catch (e: Error) {
                Log.d(TAG, "fetchAllUsers: ${e.localizedMessage}")
                _userListUiState.value = UiState.Error("Unable to fetch users")
            }
        }
    }

    fun fetchUserDetail() {
        _userDetailUiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                selectedId?.let {
                    val user = repository.getUserDetail(it)
                    val rentUser = user.toUser()
                    _userDetailUiState.value = UiState.Success(rentUser)
                }
            } catch (exception: TypeCastException) {
                Log.d(TAG, "fetchUserDetail: type cast exception")

            } catch (exception: RuntimeException) {
                Log.d(TAG, "fetchUserDetail: run time exception")
            } catch (exception: Exception) {
                Log.d(TAG, "fetchUserDetail: general exception")
            }
        }
    }
}