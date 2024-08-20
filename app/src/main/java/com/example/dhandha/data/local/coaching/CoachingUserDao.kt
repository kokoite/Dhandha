package com.example.dhandha.data.local.coaching

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CoachingUserDao  {

    @Query("SELECT * from coaching_user")
    suspend fun fetchAllStudents(): List<CoachingUserEntity>
}