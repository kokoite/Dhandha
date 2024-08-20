package com.example.dhandha.data.local.library

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "library_user")
data class LibraryUserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val phone: String,
    val address: String,
    val isActive: Boolean,
    val fees: Int,
    val pendingAmount: Int,
    val monthStartingDate: Long,
    val monthExpiringDate: Long,
    val joinedDate: Long,
)