package com.example.dhandha.ui.library.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.dhandha.data.local.gym.GymUserEntity
import com.example.dhandha.data.models.GymUserListCell
import com.example.dhandha.data.models.LibraryUser
import com.example.dhandha.data.models.LibraryUserListCell
import com.example.dhandha.data.repository.LibraryUserRepository
import com.example.dhandha.helper.convertLongDateToString
import com.example.dhandha.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


const val TAG = "TAG"
@HiltViewModel
class LibraryViewModel @Inject constructor(private val repository: LibraryUserRepository): ViewModel() {

    private var _createUserUiState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val createUserUiState: StateFlow<UiState<Boolean>> = _createUserUiState

    private var _userListState = MutableStateFlow<UiState<Flow<PagingData<LibraryUserListCell>>>>(UiState.Loading)
    val userListState: StateFlow<UiState<Flow<PagingData<LibraryUserListCell>>>> = _userListState

    private var job: Job? = null
    private var searchQuery = MutableStateFlow<String>("")

    fun createUser(user: LibraryUser) {
        viewModelScope.launch {
            try {
                _createUserUiState.value = UiState.Loading
                val entity = user.toEntity()
                repository.createUser(entity)
                _createUserUiState.value = UiState.Success(true)
            } catch (e: TypeCastException) {
                Log.d(TAG, "createUser: type case exception")
                _createUserUiState.value = UiState.Error("Something went wrong")
            } catch (e: RuntimeException) {
                Log.d(TAG, "createUser: run time exception ${e.localizedMessage}")
                _createUserUiState.value = UiState.Error("Something went wrong")
            } catch (e: Exception) {
                _createUserUiState.value = UiState.Error("Something went wrong")
            }
        }
    }

    fun updateSearchQuery() {

    }

    fun fetchAllUsers() {
        job?.cancel()
        viewModelScope.launch {
            try {
                _userListState.value = UiState.Loading
                delay(1000)
                val flow = repository.fetchAllPaginatedUsers(searchQuery.value)
                val updatedFlow = flow.map {
                    it.map {
                            entity ->
                        val expiry = convertLongDateToString(entity.monthExpiringDate)
                        LibraryUserListCell(
                            id = entity.id,
                            name = entity.name,
                            feesAmount = entity.fees.toString(),
                            phone = entity.phone,
                            pendingAmount = entity.pendingAmount.toString(),
                            expiryDate = expiry,
                            image = entity.image
                        )
                    }
                }
                _userListState.value = UiState.Success(updatedFlow)
            } catch (e: TypeCastException) {
                Log.d(com.example.dhandha.ui.gym.viewmodel.TAG, "fetchUsers: Type converter exception")
            } catch (e: CancellationException) {
                Log.d(com.example.dhandha.ui.gym.viewmodel.TAG, "fetchUsers: cancelled")
            } catch (e: Exception) {
                Log.d(com.example.dhandha.ui.gym.viewmodel.TAG, "fetchUsers: ${e.localizedMessage}")
            }
        }
    }


}