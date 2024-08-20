package com.example.dhandha.data.local.rent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RentUserDao {

    @Query("SELECT * from rent_user")
    suspend fun fetchAllUsers(): List<RentUserEntity>


    @Insert
    suspend fun insertUser(user: RentUserEntity)

}