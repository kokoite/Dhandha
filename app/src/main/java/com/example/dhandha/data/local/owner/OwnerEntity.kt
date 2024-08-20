package com.example.dhandha.data.local.owner

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "owner")
data class OwnerEntity(
    @PrimaryKey
    val id: UUID,
    val name: String,
    val phone: String,
    val email: String,
    val pendingAmount: Long,
    val earnedAmount: Long
)