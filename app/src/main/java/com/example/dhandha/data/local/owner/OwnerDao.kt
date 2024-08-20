package com.example.dhandha.data.local.owner

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OwnerDao {

    @Query("select * from owner")
    suspend fun fetchOwner(): List<OwnerEntity>
}