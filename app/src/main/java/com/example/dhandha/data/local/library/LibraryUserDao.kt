package com.example.dhandha.data.local.library

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LibraryUserDao {

    @Query("select * from library_user")
    suspend fun fetchAllLibraryUsers(): List<LibraryUserEntity>
}