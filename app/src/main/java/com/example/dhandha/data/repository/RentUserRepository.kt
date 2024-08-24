package com.example.dhandha.data.repository

import androidx.room.withTransaction
import com.example.dhandha.data.local.db.DhandhaDatabase
import com.example.dhandha.data.local.library.LibraryUserDao
import com.example.dhandha.data.local.rent.RentUserEntity
import com.example.dhandha.data.models.CreateRentUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


interface RentUserRepository {
    fun getAllUsers()
    suspend fun createUser(user: RentUserEntity)
    fun editUser()
    fun deleteUser()
}

class RentUserRepositoryImpl @Inject constructor(private val db: DhandhaDatabase) : RentUserRepository {

    override fun getAllUsers() {

    }

    override suspend fun createUser(user: RentUserEntity) {
        val dao = db.rentUserDao()
        dao.insertUser(user)
    }

    override fun editUser() {

    }

    override fun deleteUser() {

    }

}