package com.example.dhandha.data.local.rent

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RentUserDao {

    @Query("SELECT * from rent_user")
    fun fetchAllUsers(): PagingSource<Int, RentUserEntity>


    @Insert
    suspend fun insertUser(user: RentUserEntity)

}