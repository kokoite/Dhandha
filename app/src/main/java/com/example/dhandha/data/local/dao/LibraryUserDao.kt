package com.example.dhandha.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dhandha.data.local.entity.LibraryUserEntity

@Dao
interface LibraryUserDao {

    @Query("select * from library_user")
    suspend fun fetchAllLibraryUsers(): List<LibraryUserEntity>

    @Insert
    suspend fun insertUser(user: LibraryUserEntity)


    @Query ("SELECT * from library_user WHERE name LIKE '%' || :searchQuery || '%' OR phone LIKE '%' || :searchQuery || '%' ORDER BY joinedDate DESC LIMIT :limit OFFSET :offset")
    suspend fun fetchPaginatedUsers(searchQuery: String, offset: Int, limit: Int): List<LibraryUserEntity>

}