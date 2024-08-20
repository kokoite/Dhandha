package com.example.dhandha.ui.rent.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.dhandha.data.local.rent.RentUserEntity
import com.example.dhandha.data.repository.RentUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(val repository: RentUserRepository) : ViewModel() {

    fun insertUser() {
    }
}