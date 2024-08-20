package com.example.dhandha.data.local.gym

import androidx.room.Dao
import androidx.room.Query

@Dao
interface GymUserDao {

    @Query("SELECT * from gym_user")
    suspend fun fetchAllUsers(): List<GymUserEntity>


}