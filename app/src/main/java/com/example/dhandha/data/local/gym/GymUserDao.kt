package com.example.dhandha.data.local.gym

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dhandha.data.local.coaching.CoachingUserEntity

@Dao
interface GymUserDao {

    @Query("SELECT * from gym_user")
    suspend fun fetchAllUsers(): List<GymUserEntity>

    @Query ("SELECT * from gym_user WHERE name LIKE '%' || :searchQuery || '%' OR phone LIKE '%' || :searchQuery || '%' ORDER BY joinedDate DESC LIMIT :limit OFFSET :offset")
    suspend fun fetchPaginatedUsers(searchQuery: String, offset: Int, limit: Int): List<GymUserEntity>

    @Insert
    suspend fun insertUser(user: GymUserEntity)

}