package com.example.dhandha.data.local.coaching

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dhandha.data.local.rent.RentUserEntity

@Dao
interface CoachingUserDao  {

    @Query("SELECT * from coaching_user")
    suspend fun fetchAllStudents(): List<CoachingUserEntity>

    @Insert
    fun insertUser(user: CoachingUserEntity)


    @Query ("SELECT * from coaching_user WHERE name LIKE '%' || :searchQuery || '%' OR phone LIKE '%' || :searchQuery || '%' ORDER BY joinedDate DESC LIMIT :limit OFFSET :offset")
    suspend fun fetchPaginatedUsers(searchQuery: String, offset: Int, limit: Int): List<CoachingUserEntity>
}