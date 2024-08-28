package com.example.dhandha.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dhandha.data.local.entity.RentUserEntity
import java.util.UUID

@Dao
interface RentUserDao {

    @Query("SELECT * from rent_user")
    fun fetchAllUsers(): PagingSource<Int, RentUserEntity>


    @Insert
    suspend fun insertUser(user: RentUserEntity)


    @Query("SELECT * from rent_user where id = :id  LIMIT 1")
    suspend fun fetchUserDetail(id: UUID): RentUserEntity


    @Query ("SELECT * from rent_user WHERE name LIKE '%' || :searchQuery || '%' OR phone LIKE '%' || :searchQuery || '%'")
    fun searchUser(searchQuery: String): PagingSource<Int, RentUserEntity>

    @Query("SELECT * FROM rent_user WHERE name LIKE '%' || :searchQuery || '%' OR phone LIKE '%' || :searchQuery || '%' ORDER BY joinedDate DESC LIMIT :limit OFFSET :offset")
    suspend fun fetchPaginatedUsers(searchQuery: String, offset: Int, limit: Int): List<RentUserEntity>
}