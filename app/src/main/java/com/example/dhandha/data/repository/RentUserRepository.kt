package com.example.dhandha.data.repository

import androidx.room.withTransaction
import com.example.dhandha.data.local.db.DhandhaDatabase
import com.example.dhandha.data.local.library.LibraryUserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


interface RentUserRepository {
    fun getAllUsers()
    fun createUser()
    fun editUser()
    fun deleteUser()
}

class RentUserRepositoryImpl @Inject constructor(val db: DhandhaDatabase) : RentUserRepository {

    override fun getAllUsers() {

    }

    override fun createUser() {

    }

    override fun editUser() {

    }

    override fun deleteUser() {

    }

}